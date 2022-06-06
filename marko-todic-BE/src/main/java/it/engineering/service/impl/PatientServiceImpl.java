package it.engineering.service.impl;

import it.engineering.dto.*;
import it.engineering.entity.*;
import it.engineering.exception.BadRequestException;
import it.engineering.exception.ResourceNotFoundException;
import it.engineering.mapper.PatientMapper;
import it.engineering.repository.OrganizationRepository;
import it.engineering.repository.PatientRepository;
import it.engineering.repository.PractitionerRepository;
import it.engineering.service.PatientService;
import it.engineering.service.PractitionerService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PractitionerService practitionerService;

    @Autowired
    private PractitionerRepository practitionerRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    private final PatientMapper patientMapper = Mappers.getMapper(PatientMapper.class);


    @Override
    public PagedResponse<PatientSimpleDto> findAll(String filter, int pageNumber, int pageSize, String sortField, String sortDir) {
        Sort sort = Sort.by(sortField);

        sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? sort.ascending() : sort.descending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Page<Patient> patients;

        if(Arrays.stream(MaritalStatus.values()).map(Enum::name).collect(Collectors.toList()).contains(filter)){
            patients = patientRepository.findByMaritalStatus(MaritalStatus.fromString(filter), pageable);
        }
        else if(filter != null){
            patients = patientRepository.findAllByName(filter, pageable);
        }
        else{
            patients = patientRepository.findAll(pageable);
        }

        List<Patient> patientList = patients.getContent();

        List<PatientSimpleDto> patientSimpleDtos = patientList.stream().map(patient -> patientMapper.toDto(patient))
                .collect(Collectors.toList());

        PagedResponse<PatientSimpleDto> pagedResponse = new PagedResponse<>();

        pagedResponse.setContent(patientSimpleDtos);
        pagedResponse.setPageNumber(patients.getNumber());
        pagedResponse.setPageSize(patients.getSize());
        pagedResponse.setTotalElements(patients.getTotalElements());
        pagedResponse.setTotalPages(patients.getTotalPages());
        pagedResponse.setLast(patients.isLast());

        return pagedResponse;
    }

    @Override
    public List<PatientIdentifierNameDto> findAllSimple() {
        return patientRepository.findAll()
                .stream().map(patient -> patientMapper.toSimpleDto(patient))
                .collect(Collectors.toList());
    }

    @Override
    public PatientSimpleDto findById(Integer id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", id));

        return patientMapper.toDto(patient);
    }

    @Override
    public PatientFullDto findByIdView(Integer id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", id));

        return patientMapper.toFullDto(patient);
    }

    @Override
    public List<PatientIdentifierNameDto> findByOrganization(Integer id) {
        return patientRepository.findAllByOrganizationId(id)
                .stream().map(patient -> patientMapper.toSimpleDto(patient))
                .collect(Collectors.toList());
    }

    @Override
    public PatientSimpleDto save(PatientSimpleDto patientSimpleDto) {
        PractitionerSimpleDto practitioner = practitionerService.findByIdSimple(patientSimpleDto.getPractitioner());

        if(!practitioner.getQualification().equals(Qualification.DOCTOR_OF_MEDICINE)){
            ApiResponse apiResponse =
                    new ApiResponse(Boolean.FALSE, "Your primary care provider must have title Doctor of Medicine");
            throw new BadRequestException(apiResponse);
        }

        try {
            Patient patient = patientRepository.save(patientMapper.toEntity(patientSimpleDto));
            return patientMapper.toDto(patient);
        }catch (DataIntegrityViolationException exception){
            throw new DataIntegrityViolationException("Already exists entity with the same identifier");
        }
    }

    @Override
    public PatientSimpleDto update(Integer id, PatientSimpleDto patientSimpleDto){
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", id));

        Organization organization = organizationRepository.findById(patientSimpleDto.getOrganization())
                .orElseThrow(() -> new ResourceNotFoundException("Organization", "id", id));

        Practitioner practitioner = practitionerRepository.findById(patientSimpleDto.getPractitioner())
                .orElseThrow(() -> new ResourceNotFoundException("Practitioner", "id", id));


        if(!practitioner.getQualification().equals(Qualification.DOCTOR_OF_MEDICINE)){
            ApiResponse apiResponse =
                    new ApiResponse(Boolean.FALSE, "Your primary care provider must have title Doctor of Medicine");
            throw new BadRequestException(apiResponse);
        }

        patient.setIdentifier(patientSimpleDto.getIdentifier());
        patient.setActive(patientSimpleDto.getActive());
        patient.setName(patientSimpleDto.getName());
        patient.setSurname(patientSimpleDto.getSurname());
        patient.setGender(patientSimpleDto.getGender());
        patient.setBirthDate(patientSimpleDto.getBirthDate());
        patient.setAddress(patientSimpleDto.getAddress());
        patient.setPhone(patientSimpleDto.getPhone());
        patient.setEmail(patientSimpleDto.getEmail());
        patient.setMaritalStatus(patientSimpleDto.getMaritalStatus());
        patient.setPractitioner(practitioner);
        patient.setOrganization(organization);

        try {
            Patient updatedPatient = patientRepository.save(patient);
            return patientMapper.toDto(updatedPatient);
        }catch (DataIntegrityViolationException exception){
            throw new DataIntegrityViolationException("Already exists entity with the same identifier");
        }

    }

    @Override
    public ApiResponse delete(Integer id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", id));

        long numberOfExaminations = patient.getExaminations().stream()
                .filter(examination -> examination.getStatus().equals(Status.IN_PROGRESS)).count();

        if(numberOfExaminations > 0){
            ApiResponse apiResponse =
                    new ApiResponse(Boolean.FALSE, "Cannot delete patient because there are examinations in the RUNNING state");
            throw new BadRequestException(apiResponse);
        }

        LocalDateTime currentTime = LocalDateTime.now();
        Instant instant = Timestamp.valueOf(currentTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).toInstant();

        ApiResponse apiResponse;

        if(patient.getExaminations().stream().map(examination -> examination.getStartDate().before(Date.from(instant))).count() > 0
            && patient.getExaminations().stream().map(examination -> examination.getStartDate().after(Date.from(instant))).count() > 0){
            apiResponse = new ApiResponse(Boolean.FALSE, "you cannot delete a patient because there are examinations in executing faze");
            throw new BadRequestException(apiResponse);
        }

        patientRepository.delete(id);

        return new ApiResponse(Boolean.TRUE, "Patient with id: " + id + " deleted successfully");
    }
}
