package it.engineering.service;

import it.engineering.dto.ApiResponse;
import it.engineering.dto.PagedResponse;
import it.engineering.dto.PatientFullDto;
import it.engineering.dto.PatientSimpleDto;
import it.engineering.exception.SqlException;

import java.sql.SQLException;

public interface PatientService {
    PagedResponse<PatientSimpleDto> findAll(String filter, int pageNumber, int pageSize, String sortField, String sortDir);

    PatientSimpleDto findById(Integer id);
    PatientFullDto findByIdView(Integer id);
    PatientSimpleDto save(PatientSimpleDto patientSimpleDto);
    PatientSimpleDto update(Integer id, PatientSimpleDto patientSimpleDto);
    ApiResponse delete(Integer id);
}
