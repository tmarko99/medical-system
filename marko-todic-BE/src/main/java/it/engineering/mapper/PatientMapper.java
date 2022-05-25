package it.engineering.mapper;

import it.engineering.dto.PatientSimpleDto;
import it.engineering.entity.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PatientMapper {
    @Mapping(target = "examinations", ignore = true)
    @Mapping(target = "practitioner.id", source = "practitioner")
    @Mapping(target = "organization.id", source = "organization")
    Patient toEntity(PatientSimpleDto patientSimpleDto);

    @Mapping(target = "organization", source = "organization.id")
    @Mapping(target = "practitioner", source = "practitioner.id")
    PatientSimpleDto toDto(Patient patient);
}
