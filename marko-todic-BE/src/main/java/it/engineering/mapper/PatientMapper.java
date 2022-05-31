package it.engineering.mapper;

import it.engineering.dto.*;
import it.engineering.entity.Examination;
import it.engineering.entity.Organization;
import it.engineering.entity.Patient;
import it.engineering.entity.Practitioner;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface PatientMapper {

    ExaminationMapper INSTANCE = Mappers.getMapper(ExaminationMapper.class);
    PractitionerMapper INSTANCE_PRACTITIONER = Mappers.getMapper(PractitionerMapper.class);
    OrganizationMapper INSTANCE_ORGANIZATION = Mappers.getMapper(OrganizationMapper.class);

    @Mapping(target = "examinations", ignore = true)
    @Mapping(target = "practitioner.id", source = "practitioner")
    @Mapping(target = "organization.id", source = "organization")
    Patient toEntity(PatientSimpleDto patientSimpleDto);

    @Mapping(target = "name", expression = "java(patient.getName() + ' ' + patient.getSurname())")
    PatientIdentifierNameDto toSimpleDto(Patient patient);

    @Mapping(target = "organization", source = "organization.id")
    @Mapping(target = "practitioner", source = "practitioner.id")
    PatientSimpleDto toDto(Patient patient);

    @Mapping(target = "examinations", expression = "java(toDtoConverterExamination(patient.getExaminations()))")
    @Mapping(target = "practitioner", expression = "java(toDtoConverterPractitioner(patient.getPractitioner()))")
    @Mapping(target = "organization", expression = "java(toDtoConverterOrganization(patient.getOrganization()))")
    PatientFullDto toFullDto(Patient patient);

    default List<ExaminationDto> toDtoConverterExamination(List<Examination> examinations){
        return examinations.stream().map(examination -> INSTANCE.toDto(examination)).collect(Collectors.toList());
    }

    default PractitionerSimpleDto toDtoConverterPractitioner(Practitioner practitioner){
        return INSTANCE_PRACTITIONER.toDto(practitioner);
    }

    default OrganizationDto toDtoConverterOrganization(Organization organization){
        return INSTANCE_ORGANIZATION.toDto(organization);
    }
}
