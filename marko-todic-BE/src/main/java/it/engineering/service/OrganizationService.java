package it.engineering.service;

import it.engineering.dto.*;
import it.engineering.entity.OrganizationType;

import java.util.List;

public interface OrganizationService {
    OrganizationDto save(OrganizationDto organizationDto);
    PagedResponse<OrganizationDto> findAll(OrganizationType filter, int pageNumber, int pageSize, String sortField, String sortDir);
    List<OrganizationIdentifierNameDto> findAllSimple();
    OrganizationDto update(Integer id, OrganizationDto newOrganization);
    OrganizationFullDto findByIdView(Integer id);
    OrganizationDto findByIdEdit(Integer id);
    ApiResponse delete(Integer id);
}
