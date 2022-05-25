package it.engineering.service;

import it.engineering.dto.ApiResponse;
import it.engineering.dto.PagedResponse;
import it.engineering.dto.PatientSimpleDto;

public interface PatientService {
    PagedResponse<PatientSimpleDto> findAll(int pageNumber, int pageSize, String sortField, String sortDir);
    PatientSimpleDto save(PatientSimpleDto patientSimpleDto);
    PatientSimpleDto update(Integer id, PatientSimpleDto patientSimpleDto);
    ApiResponse delete(Integer id);
}
