package it.engineering.mapper;

import it.engineering.dto.OrganizationDto;
import it.engineering.dto.OrganizationFullDto;
import it.engineering.dto.OrganizationIdentifierNameDto;
import it.engineering.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(uses = { PractitionerMapper.class, PatientMapper.class, ExaminationMapper.class })
public interface OrganizationMapper {
    @Mapping(target = "practitioners", ignore = true)
    @Mapping(target = "patients", ignore = true)
    @Mapping(target = "examinations", ignore = true)
    @Mapping(target = "type", expression = "java(getOrganizationType(organizationDto.getType().name()))")
    Organization toEntity(OrganizationDto organizationDto);

    @Mapping(target = "numberOfPractitioners", expression = "java(getActiveNumberOfPractitioners(organization.getPractitioners()))")
    @Mapping(target = "numberOfPatients", expression = "java(getActiveNumberOfPatients(organization.getPatients()))")
    @Mapping(target = "numberOfExaminations", expression = "java(getValidNumberOfExaminations(organization.getExaminations()))")
    OrganizationDto toDto(Organization organization);

    OrganizationIdentifierNameDto toSimpleDto(Organization organization);


    @Mapping(target = "examinations.organization", ignore = true)
    OrganizationFullDto toFullDto(Organization organization);


    default OrganizationType getOrganizationType(String type){
        return OrganizationType.fromString(type);
    }

    default Integer getValidNumberOfExaminations(List<Examination> examinations){
        return Math.toIntExact(examinations.stream().filter(examination -> !examination.getStatus().equals(Status.ENTERED_IN_ERROR)).count());
    }

    default Integer getActiveNumberOfPatients(List<Patient> patients){
        return Math.toIntExact(patients.stream().filter(patient -> patient.getActive().equals(true)).count());
    }

    default Integer getActiveNumberOfPractitioners(List<Practitioner> practitioners){
        return Math.toIntExact(practitioners.stream().filter(practitioner -> practitioner.getActive().equals(true)).count());
    }
}
