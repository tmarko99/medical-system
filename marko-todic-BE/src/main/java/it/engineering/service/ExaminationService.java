package it.engineering.service;

import it.engineering.dto.ApiResponse;
import it.engineering.dto.ExaminationDto;
import it.engineering.dto.ExaminationFullDto;
import it.engineering.dto.PagedResponse;

public interface ExaminationService {
    PagedResponse<ExaminationDto> findAll(int pageNumber, int pageSize, String sortField, String sortDir);
    ExaminationFullDto findById(Integer id);
    ExaminationDto save(ExaminationDto examinationDto);

    ApiResponse delete(Integer id);
}
