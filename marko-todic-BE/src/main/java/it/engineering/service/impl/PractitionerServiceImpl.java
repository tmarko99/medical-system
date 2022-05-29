package it.engineering.service.impl;

import it.engineering.dto.*;
import it.engineering.entity.*;
import it.engineering.exception.BadRequestException;
import it.engineering.exception.ResourceNotFoundException;
import it.engineering.mapper.PractitionerMapper;
import it.engineering.repository.PatientRepository;
import it.engineering.repository.PractitionerRepository;
import it.engineering.service.PractitionerService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EnumType;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PractitionerServiceImpl implements PractitionerService {
    @Autowired
    private PractitionerRepository practitionerRepository;

    @Autowired
    private PatientRepository patientRepository;

    private final PractitionerMapper practitionerMapper = Mappers.getMapper(PractitionerMapper.class);

    @Override
    public PagedResponse<PractitionerSimpleDto> findAll(String filter, int pageNumber, int pageSize, String sortField, String sortDir) {
        Sort sort = Sort.by(sortField);

        sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? sort.ascending() : sort.descending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Page<Practitioner> practitioners;

        if(Arrays.stream(Gender.values()).map(Enum::name).collect(Collectors.toList()).contains(filter)){
            practitioners = practitionerRepository.findAllByGender(Gender.fromString(filter), pageable);
        }
        else if(Arrays.stream(Qualification.values()).map(Enum::name).collect(Collectors.toList()).contains(filter)){
            practitioners = practitionerRepository.findAllByQualification(Qualification.fromString(filter), pageable);
        }
        else if(filter != null){
            practitioners = practitionerRepository.findAllByNameContainingAndSurnameContaining(filter, pageable);
        }
        else{
            practitioners = practitionerRepository.findAll(pageable);
        }

        List<Practitioner> practitionerList = practitioners.getContent();

        List<PractitionerSimpleDto> practitionerSimpleDtos = practitionerList.stream().map(practitioner -> practitionerMapper.toDto(practitioner))
                .collect(Collectors.toList());

        PagedResponse<PractitionerSimpleDto> pagedResponse = new PagedResponse<>();

        pagedResponse.setContent(practitionerSimpleDtos);
        pagedResponse.setPageNumber(practitioners.getNumber());
        pagedResponse.setPageSize(practitioners.getSize());
        pagedResponse.setTotalElements(practitioners.getTotalElements());
        pagedResponse.setTotalPages(practitioners.getTotalPages());
        pagedResponse.setLast(practitioners.isLast());

        return pagedResponse;
    }

    @Override
    public List<PractitionerIdentifierNameDto> findAllSimple() {
        return practitionerRepository.findAll().stream()
                .map(practitioner -> practitionerMapper.toSimpleDto(practitioner))
                .collect(Collectors.toList());
    }

    @Override
    public PractitionerFullDto findByIdView(Integer id) {
        Practitioner practitioner = practitionerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Practitioner", "id", id));

        return practitionerMapper.toFullDto(practitioner);
    }

    @Override
    public PractitionerSimpleDto findByIdSimple(Integer id) {
        Practitioner practitioner = practitionerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Practitioner", "id", id));

        return practitionerMapper.toDto(practitioner);
    }

    @Override
    public List<PractitionerIdentifierNameDto> findByOrganization(Integer id) {
        return practitionerRepository.findAllByOrganizationId(id)
                .stream().map(practitioner -> practitionerMapper.toSimpleDto(practitioner))
                .collect(Collectors.toList());
    }

    @Override
    public PractitionerSimpleDto save(PractitionerSimpleDto practitionerSimpleDto) {
        Practitioner practitioner = practitionerRepository.save(practitionerMapper.toEntity(practitionerSimpleDto));

        return practitionerMapper.toDto(practitioner);
    }

    @Override
    public PractitionerSimpleDto update(Integer id, PractitionerSimpleDto practitionerSimpleDto) {
        Practitioner practitioner = practitionerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Practitioner", "id", id));

        List<Practitioner> practitioners = practitionerRepository.findAll();

        long count = practitioners.stream().filter(pract -> pract.getIdentifier()
                .equalsIgnoreCase(practitionerSimpleDto.getIdentifier())).count();

        if(count > 1){
            ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "Already exists entry with same identifier");
            throw new BadRequestException(apiResponse);
        }

        practitioner.setIdentifier(practitionerSimpleDto.getIdentifier());
        practitioner.setActive(practitionerSimpleDto.getActive());
        practitioner.setName(practitionerSimpleDto.getName());
        practitioner.setSurname(practitionerSimpleDto.getSurname());
        practitioner.setGender(practitionerSimpleDto.getGender());
        practitioner.setBirthDate(practitioner.getBirthDate());
        practitioner.setAddress(practitionerSimpleDto.getAddress());
        practitioner.setPhone(practitionerSimpleDto.getPhone());
        practitioner.setEmail(practitionerSimpleDto.getEmail());
        practitioner.setQualification(practitionerSimpleDto.getQualification());


        Practitioner updatedPractitioner = practitionerRepository.save(practitioner);

        return practitionerMapper.toDto(updatedPractitioner);
    }

    @Override
    public ApiResponse delete(Integer id) {
        Practitioner practitioner = practitionerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Practitioner", "id", id));

        long numberOfExaminations = practitioner.getExaminations().stream()
                .filter(examination -> examination.getStatus().equals(Status.IN_PROGRESS)).count();

        if(numberOfExaminations > 0){
            ApiResponse apiResponse =
                    new ApiResponse(Boolean.FALSE, "Cannot delete practitioner because there are examinations in the RUNNING state");
            throw new BadRequestException(apiResponse);
        }

        for (Patient patient : practitioner.getPatients()){
            this.patientRepository.delete(patient.getId());
        }

        practitionerRepository.delete(id);

        return new ApiResponse(Boolean.TRUE, "Practitioner with id: " + id + " deleted successfully");
    }

}
