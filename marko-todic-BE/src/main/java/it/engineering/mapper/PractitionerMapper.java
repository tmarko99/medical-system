package it.engineering.mapper;

import it.engineering.dto.PractitionerFullDto;
import it.engineering.dto.PractitionerIdentifierNameDto;
import it.engineering.dto.PractitionerSimpleDto;
import it.engineering.entity.Practitioner;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses= { PatientMapper.class, ExaminationMapper.class })
public interface PractitionerMapper {
    @Mapping(target = "patients", ignore = true)
    @Mapping(target = "examinations", ignore = true)
    @Mapping(target = "organization.id", source = "organization")
    Practitioner toEntity(PractitionerSimpleDto practitionerSimpleDto);

    @Mapping(target = "organization", source = "organization.id")
    @Mapping(target = "numberOfPatients", expression = "java(practitioner.getPatients() != null ? practitioner.getPatients().size() : 0)")
    PractitionerSimpleDto toDto(Practitioner practitioner);

    @Mapping(target = "name", expression = "java(practitioner.getName() + ' ' + practitioner.getSurname())")
    PractitionerIdentifierNameDto toSimpleDto(Practitioner practitioner);

    @Mapping(target = "examinations", source = "examinations")
    @Mapping(target = "organization", source = "organization")
    PractitionerFullDto toFullDto(Practitioner practitioner);
}
