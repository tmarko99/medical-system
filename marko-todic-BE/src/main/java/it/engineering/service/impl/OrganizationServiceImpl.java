package it.engineering.service.impl;

import it.engineering.dto.ApiResponse;
import it.engineering.dto.OrganizationDto;
import it.engineering.dto.PagedResponse;
import it.engineering.entity.Organization;
import it.engineering.entity.Status;
import it.engineering.exception.BadRequestException;
import it.engineering.exception.ResourceNotFoundException;
import it.engineering.mapper.OrganizationMapper;
import it.engineering.repository.OrganizationRepository;
import it.engineering.service.OrganizationService;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.nio.file.ReadOnlyFileSystemException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    private final OrganizationMapper organizationMapper = Mappers.getMapper(OrganizationMapper.class);

    @Override
    public OrganizationDto save(OrganizationDto organizationDto) {
//        Organization organization = new Organization();
//
//        organization.setIdentifier(organizationDto.getIdentifier());
//        organization.setActive(organizationDto.getActive());
//        organization.setType(organizationDto.getType());
//        organization.setName(organizationDto.getName());
//        organization.setAddress(organizationDto.getAddress());
//        organization.setPhone(organizationDto.getPhone());
//        organization.setEmail(organizationDto.getEmail());

        Organization organization = organizationRepository.save(organizationMapper.toEntity(organizationDto));

        return organizationMapper.toDto(organization);
    }

    @Override
    public PagedResponse<OrganizationDto> findAll(int pageNumber, int pageSize, String sortField, String sortDir) {
        Sort sort = Sort.by(sortField);

        sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? sort.ascending() : sort.descending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Page<Organization> organizations = organizationRepository.findAll(pageable);

        List<Organization> organizationList = organizations.getContent();

        List<OrganizationDto> organizationDtos = organizationList.stream()
                .map(organization -> organizationMapper.toDto(organization)).collect(Collectors.toList());

        PagedResponse<OrganizationDto> pagedResponse = new PagedResponse<>();

        pagedResponse.setContent(organizationDtos);
        pagedResponse.setPageNumber(organizations.getNumber());
        pagedResponse.setPageSize(organizations.getSize());
        pagedResponse.setTotalElements(organizations.getTotalElements());
        pagedResponse.setTotalPages(organizations.getTotalPages());
        pagedResponse.setLast(organizations.isLast());

        return pagedResponse;

    }

    @Override
    public OrganizationDto findById(Integer id) {
        Organization organization = organizationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Organization", "id", id));

        return organizationMapper.toDto(organization);
    }

    @Override
    public OrganizationDto update(Integer id, OrganizationDto newOrganization) {
        Organization organization = organizationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Organization", "id", id));

        List<Organization> organizations = organizationRepository.findAll();

        long count = organizations.stream().filter(org -> org.getIdentifier().equalsIgnoreCase(newOrganization.getIdentifier()) &&
                org.getName().equalsIgnoreCase(newOrganization.getName())).count();


        if(count > 0){
            ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "Duplicate entry");
            throw new BadRequestException(apiResponse);
        }

        organization.setIdentifier(newOrganization.getIdentifier());
        organization.setActive(newOrganization.getActive());
        organization.setType(newOrganization.getType());
        organization.setName(newOrganization.getName());
        organization.setAddress(newOrganization.getAddress());
        organization.setPhone(newOrganization.getPhone());
        organization.setEmail(newOrganization.getEmail());


        Organization updatedOrganization = organizationRepository.save(organization);

        return organizationMapper.toDto(updatedOrganization);
    }

    @Override
    public ApiResponse delete(Integer id) {
        Organization organization = organizationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Organization", "id", id));

        long numberOfExaminations = organization.getExaminations().stream()
                .filter(examination -> examination.getStatus().equals(Status.IN_PROGRESS)).count();

        if(numberOfExaminations > 0){
            ApiResponse apiResponse =
                    new ApiResponse(Boolean.FALSE, "Cannot delete organization because there are examinations in the RUNNING state");
            throw new BadRequestException(apiResponse);
        }

        organizationRepository.delete(id);

        return new ApiResponse(Boolean.TRUE, "Organization with id: " + id + " deleted successfully");


    }


}