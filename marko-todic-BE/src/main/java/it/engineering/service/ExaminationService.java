package it.engineering.service;

import it.engineering.dto.*;
import it.engineering.security.UserPrincipal;

public interface ExaminationService {
    PagedResponse<ExaminationDto> findAll(FilterDto filterDto, int pageNumber, int pageSize, String sortField, String sortDir);
    ExaminationFullDto findByIdView(Integer id);
    ExaminationDto findById(Integer id);
    ExaminationDto save(ExaminationDto examinationDto);
    ExaminationDto update(Integer id, ExaminationDto examinationDto);

    ApiResponse delete(Integer id);
}
