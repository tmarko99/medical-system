package it.engineering.service;

import it.engineering.dto.*;

import java.util.List;

public interface PractitionerService {
    PagedResponse<PractitionerSimpleDto> findAll(String filter, int pageNumber, int pageSize, String sortField, String sortDir);
    List<PractitionerIdentifierNameDto> findAllSimple();
    PractitionerSimpleDto save(PractitionerSimpleDto practitionerSimpleDto);
    PractitionerSimpleDto update(Integer id, PractitionerSimpleDto practitionerSimpleDto);
    PractitionerFullDto findByIdView(Integer id);

    PractitionerSimpleDto findByIdSimple(Integer id);

    List<PractitionerIdentifierNameDto> findByOrganization(Integer id);

    ApiResponse delete(Integer id);
}
