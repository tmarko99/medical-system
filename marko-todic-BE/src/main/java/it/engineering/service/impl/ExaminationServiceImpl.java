package it.engineering.service.impl;

import it.engineering.dto.*;
import it.engineering.entity.*;
import it.engineering.exception.BadRequestException;
import it.engineering.exception.ResourceNotFoundException;
import it.engineering.mapper.ExaminationMapper;
import it.engineering.repository.*;
import it.engineering.service.ExaminationService;
import it.engineering.specification.ExaminationSpecification;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExaminationServiceImpl implements ExaminationService {
    @Autowired
    private ExaminationRepository examinationRepository;

    @Autowired
    private PractitionerRepository practitionerRepository;

    @Autowired
    private ServiceTypeRepository serviceTypeRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ExaminationSpecification examinationSpecification;

    private final ExaminationMapper examinationMapper = Mappers.getMapper(ExaminationMapper.class);

    @Override
    public PagedResponse<ExaminationDto> findAll(FilterDto filterDto, int pageNumber, int pageSize, String sortField, String sortDir) {
        Sort sort = Sort.by(sortField);

        sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? sort.ascending() : sort.descending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Page<Examination> examinations = examinationRepository.findAll(examinationSpecification.getExaminations(filterDto), pageable);

        List<Examination> examinationList = examinations.getContent().stream()
                .filter(examination -> !examination.getStatus().equals(Status.ENTERED_IN_ERROR))
                .collect(Collectors.toList());

        List<ExaminationDto> examinationDtos = examinationList.stream().map(examination -> examinationMapper.toDto(examination))
                .collect(Collectors.toList());

        PagedResponse<ExaminationDto> pagedResponse = new PagedResponse<>();

        pagedResponse.setContent(examinationDtos);
        pagedResponse.setPageNumber(examinations.getNumber());
        pagedResponse.setPageSize(examinations.getSize());
        pagedResponse.setTotalElements(examinations.getTotalElements());
        pagedResponse.setTotalPages(examinations.getTotalPages());
        pagedResponse.setLast(examinations.isLast());

        return pagedResponse;
    }

    @Override
    public ExaminationFullDto findByIdView(Integer id) {
        Examination examination = examinationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Examination", "id", id));

        return examinationMapper.toFullDto(examination);
    }

    @Override
    public ExaminationDto findById(Integer id) {
        Examination examination = examinationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Examination", "id", id));

        return examinationMapper.toDto(examination);
    }

    @Override
    public ExaminationDto save(ExaminationDto examinationDto) {
        Examination examination = new Examination();

        List<Practitioner> practitioners = new ArrayList<>(examinationDto.getPractitioners().size());

        ServiceType serviceType = serviceTypeRepository.findById(examinationDto.getServiceType()).
                orElseThrow(() -> new ResourceNotFoundException("ServiceType", "id", examinationDto.getServiceType()));

        Organization organization = organizationRepository.findById(examinationDto.getOrganization())
                .orElseThrow(() -> new ResourceNotFoundException("Organization", "id", examinationDto.getOrganization()));


        Patient patient = patientRepository.findById(examinationDto.getPatient())
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", examinationDto.getPatient()));

        examinationDto.getPractitioners().forEach(id -> {
            Practitioner practitioner = practitionerRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Practitioner", "id", id));

            practitioners.add(practitioner);
        });

        examination.setIdentifier(examinationDto.getIdentifier());
        examination.setStatus(examinationDto.getStatus());
        examination.setServiceType(serviceType);
        examination.setPriority(examinationDto.getPriority());
        examination.setStartDate(examinationDto.getStartDate());
        examination.setEndDate(examinationDto.getEndDate());
        examination.setDiagnosis(examinationDto.getDiagnosis());
        examination.setOrganization(organization);
        examination.setPractitioners(practitioners);
        examination.setPatient(patient);

        try {
            Examination savedExamination = examinationRepository.save(examination);
            return examinationMapper.toDto(savedExamination);
        }catch (DataIntegrityViolationException exception){
            throw new DataIntegrityViolationException("Already exists entity with the same identifier");
        }
    }

    @Override
    public ExaminationDto update(Integer id, ExaminationDto examinationDto) {
        Examination examination = examinationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Examination", "id", id));

        List<Practitioner> practitioners = new ArrayList<>(examinationDto.getPractitioners().size());

        ServiceType serviceType = serviceTypeRepository.findById(examinationDto.getServiceType()).
                orElseThrow(() -> new ResourceNotFoundException("ServiceType", "id", examinationDto.getServiceType()));

        Organization organization = organizationRepository.findById(examinationDto.getOrganization())
                .orElseThrow(() -> new ResourceNotFoundException("Organization", "id", examinationDto.getOrganization()));


        Patient patient = patientRepository.findById(examinationDto.getPatient())
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", examinationDto.getPatient()));

        examinationDto.getPractitioners().forEach(practitionerId -> {
            Practitioner practitioner = practitionerRepository.findById(practitionerId)
                    .orElseThrow(() -> new ResourceNotFoundException("Practitioner", "id", practitionerId));

            practitioners.add(practitioner);
        });

        examination.setIdentifier(examinationDto.getIdentifier());
        examination.setStatus(examinationDto.getStatus());
        examination.setServiceType(serviceType);
        examination.setPriority(examinationDto.getPriority());
        examination.setStartDate(examinationDto.getStartDate());
        examination.setEndDate(examinationDto.getEndDate());
        examination.setDiagnosis(examinationDto.getDiagnosis());
        examination.setOrganization(organization);
        examination.setPractitioners(practitioners);
        examination.setPatient(patient);

        try {
            Examination updatedExamination = examinationRepository.save(examination);
            return examinationMapper.toDto(updatedExamination);
        }catch (DataIntegrityViolationException exception){
            throw new DataIntegrityViolationException("Already exists entity with the same identifier");
        }
    }

    @Override
    public ApiResponse delete(Integer id) {
        Examination examination = examinationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Examination", "id", id));

        LocalDateTime currentTime = LocalDateTime.now();
        Instant instant = Timestamp.valueOf(currentTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).toInstant();

        ApiResponse apiResponse;

        if(examination.getStartDate().before(Date.from(instant))
                && examination.getEndDate().after(Date.from(instant))){
            apiResponse = new ApiResponse(Boolean.FALSE, "You cannot delete an examination because is not completed");
            throw new BadRequestException(apiResponse);
        }

        examinationRepository.delete(id);

        return new ApiResponse(Boolean.TRUE, "Examination with id: " + id + " deleted successfully");
    }
}
