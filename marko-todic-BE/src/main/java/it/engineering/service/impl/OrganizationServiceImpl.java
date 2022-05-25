package it.engineering.service.impl;

import it.engineering.dto.OrganizationDto;
import it.engineering.entity.Organization;
import it.engineering.mapper.OrganizationMapper;
import it.engineering.repository.OrganizationRepository;
import it.engineering.service.OrganizationService;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<OrganizationDto> findAll() {
        return organizationRepository.findAll().stream()
                .map(organization -> organizationMapper.toDto(organization)).collect(Collectors.toList());

    }

}
