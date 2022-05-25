package it.engineering.service;

import it.engineering.dto.OrganizationDto;

import java.util.List;

public interface OrganizationService {
    OrganizationDto save(OrganizationDto organizationDto);
    List<OrganizationDto> findAll();
}
