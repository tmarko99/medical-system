package it.engineering.mapper;

import it.engineering.dto.OrganizationDto;
import it.engineering.entity.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrganizationMapper {
    @Mapping(target = "practitioners", ignore = true)
    @Mapping(target = "patients", ignore = true)
    @Mapping(target = "examinations", ignore = true)
    Organization toEntity(OrganizationDto organizationDto);

    @Mapping(target = "numberOfPractitioners", expression = "java(organization.getPractitioners() != null ? organization.getPractitioners().size() : 0)")
    @Mapping(target = "numberOfPatients", expression = "java(organization.getPatients() != null ? organization.getPatients().size() : 0)")
    @Mapping(target = "numberOfExaminations", expression = "java(organization.getExaminations() != null ? organization.getExaminations().size() : 0)")
    OrganizationDto toDto(Organization organization);
}
