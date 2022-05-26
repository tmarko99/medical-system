package it.engineering.mapper;

import it.engineering.dto.ExaminationDto;
import it.engineering.dto.ExaminationFullDto;
import it.engineering.dto.PractitionerSimpleDto;
import it.engineering.entity.Examination;
import it.engineering.entity.Practitioner;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(uses = {PatientMapper.class})
public interface ExaminationMapper {
//    @Mapping(target = "organization.id", source = "organization")
//    @Mapping(target = "practitioners.id", source = "practitioners")
//    @Mapping(target = "patient.id", source = "patient")
//    @Mapping(target = "serviceType.id", source = "serviceType")
//    Examination toEntity(ExaminationDto examinationDto);

    PractitionerMapper INSTANCE = Mappers.getMapper(PractitionerMapper.class);

    @Mapping(target = "practitioners", expression = "java(toSimpleDtos(examination.getPractitioners()))")
    ExaminationFullDto toFullDto(Examination examination);

    @Mapping(target = "organization", source = "organization.id")
    @Mapping(target = "practitioners", expression = "java(getPract(examination))")
    @Mapping(target = "patient", source = "patient.id")
    @Mapping(target = "serviceType", source = "serviceType.id")
    ExaminationDto toDto(Examination examination);


    default List<PractitionerSimpleDto> toSimpleDtos(List<Practitioner> practitioners){
        return practitioners.stream().map(practitioner -> INSTANCE.toDto(practitioner)).collect(Collectors.toList());
    }

    default List<Integer> getPract(Examination examination) {
        return examination.getPractitioners().stream().map(practitioner -> practitioner.getId()).collect(Collectors.toList());
    }

}
