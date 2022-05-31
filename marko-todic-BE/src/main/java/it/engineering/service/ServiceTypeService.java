package it.engineering.service;

import it.engineering.dto.ServiceTypeDto;

import java.util.List;

public interface ServiceTypeService {
    List<ServiceTypeDto> findAll();
    ServiceTypeDto findById(Integer id);

}
