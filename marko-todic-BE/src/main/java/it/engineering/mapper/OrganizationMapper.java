package it.engineering.mapper;

import it.engineering.dto.OrganizationDto;
import it.engineering.dto.OrganizationFullDto;
import it.engineering.dto.OrganizationIdentifierNameDto;
import it.engineering.entity.Organization;
import it.engineering.entity.OrganizationType;
import it.engineering.entity.Practitioner;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = { PractitionerMapper.class, PatientMapper.class, ExaminationMapper.class })
public interface OrganizationMapper {
    @Mapping(target = "practitioners", ignore = true)
    @Mapping(target = "patients", ignore = true)
    @Mapping(target = "examinations", ignore = true)
    @Mapping(target = "type", expression = "java(getOrganizationType(organizationDto.getType().name()))")
    Organization toEntity(OrganizationDto organizationDto);

    @Mapping(target = "numberOfPractitioners", expression = "java(getActiveNumberOfPractitioners(organization.getPractitioners()))")
    @Mapping(target = "numberOfPatients", expression = "java(organization.getPatients() != null ? organization.getPatients().size() : 0)")
    @Mapping(target = "numberOfExaminations", expression = "java(organization.getExaminations() != null ? organization.getExaminations().size() : 0)")
    OrganizationDto toDto(Organization organization);

    OrganizationIdentifierNameDto toSimpleDto(Organization organization);


    @Mapping(target = "examinations.organization", ignore = true)
    OrganizationFullDto toFullDto(Organization organization);


    default OrganizationType getOrganizationType(String type){
        return OrganizationType.fromString(type);
    }

    default Integer getActiveNumberOfPractitioners(List<Practitioner> practitioners){
        return Math.toIntExact(practitioners.stream().filter(practitioner -> practitioner.getActive().equals(true)).count());
    }
}
