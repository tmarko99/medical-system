package it.engineering.service;

import it.engineering.dto.ApiResponse;
import it.engineering.dto.OrganizationDto;
import it.engineering.dto.OrganizationFullDto;
import it.engineering.dto.PagedResponse;

import java.util.List;

public interface OrganizationService {
    OrganizationDto save(OrganizationDto organizationDto);
    PagedResponse<OrganizationDto> findAll(int pageNumber, int pageSize, String sortField, String sortDir);
    OrganizationDto update(Integer id, OrganizationDto newOrganization);
    OrganizationFullDto findByIdView(Integer id);
    OrganizationDto findByIdEdit(Integer id);
    ApiResponse delete(Integer id);
}
