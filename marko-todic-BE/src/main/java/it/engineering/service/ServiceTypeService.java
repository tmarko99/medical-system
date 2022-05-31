package it.engineering.service;

import it.engineering.dto.ApiResponse;
import it.engineering.dto.ServiceTypeDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ServiceTypeService {
    List<ServiceTypeDto> findAll();
    ServiceTypeDto findById(Integer id);
    ApiResponse save(MultipartFile file);
}
