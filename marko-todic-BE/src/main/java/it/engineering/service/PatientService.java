package it.engineering.service;

import it.engineering.dto.*;

import java.util.List;

public interface PatientService {
    PagedResponse<PatientSimpleDto> findAll(String filter, int pageNumber, int pageSize, String sortField, String sortDir);

    List<PatientIdentifierNameDto> findAllSimple();
    PatientSimpleDto findById(Integer id);
    PatientFullDto findByIdView(Integer id);

    List<PatientIdentifierNameDto> findByOrganization(Integer id);
    PatientSimpleDto save(PatientSimpleDto patientSimpleDto);
    PatientSimpleDto update(Integer id, PatientSimpleDto patientSimpleDto);
    ApiResponse delete(Integer id);
}
