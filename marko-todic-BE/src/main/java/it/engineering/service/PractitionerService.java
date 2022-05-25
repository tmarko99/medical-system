package it.engineering.service;

import it.engineering.dto.ApiResponse;
import it.engineering.dto.PagedResponse;
import it.engineering.dto.PractitionerFullDto;
import it.engineering.dto.PractitionerSimpleDto;

public interface PractitionerService {
    PagedResponse<PractitionerSimpleDto> findAll(int pageNumber, int pageSize, String sortField, String sortDir);
    PractitionerSimpleDto save(PractitionerSimpleDto practitionerSimpleDto);
    PractitionerSimpleDto update(Integer id, PractitionerSimpleDto practitionerSimpleDto);
    PractitionerFullDto findById(Integer id);

    ApiResponse delete(Integer id);
}
