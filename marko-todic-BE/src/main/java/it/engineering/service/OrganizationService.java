package it.engineering.service;

import it.engineering.dto.ApiResponse;
import it.engineering.dto.OrganizationDto;
import it.engineering.dto.PagedResponse;

import java.util.List;

public interface OrganizationService {
    OrganizationDto save(OrganizationDto organizationDto);
    PagedResponse<OrganizationDto> findAll(int pageNumber, int pageSize, String sortField, String sortDir);
    OrganizationDto update(Integer id, OrganizationDto newOrganization);
    OrganizationDto findById(Integer id);
    ApiResponse delete(Integer id);
}
