package it.engineering.service.impl;

import it.engineering.dto.ApiResponse;
import it.engineering.dto.ServiceTypeDto;
import it.engineering.entity.ServiceType;
import it.engineering.exception.ResourceNotFoundException;
import it.engineering.mapper.ServiceTypeMapper;
import it.engineering.repository.ServiceTypeRepository;
import it.engineering.service.ServiceTypeService;
import it.engineering.utils.CSVHelper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceTypeServiceImpl implements ServiceTypeService {

    @Autowired
    private ServiceTypeRepository serviceTypeRepository;

    private final ServiceTypeMapper serviceTypeMapper = Mappers.getMapper(ServiceTypeMapper.class);

    @Override
    public List<ServiceTypeDto> findAll() {
        return serviceTypeRepository.findAll()
                .stream().map(serviceType -> serviceTypeMapper.toDto(serviceType))
                .collect(Collectors.toList());

    }

    @Override
    public ServiceTypeDto findById(Integer id) {
        ServiceType serviceType = serviceTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service type", "id", id));

        return serviceTypeMapper.toDto(serviceType);
    }

    @Override
    public ApiResponse save(MultipartFile file) {
        try {
            List<ServiceType> serviceTypes = CSVHelper.csvToServiceType(file.getInputStream());
            serviceTypeRepository.saveAll(serviceTypes);
            return new ApiResponse(Boolean.TRUE, "Data inserted successfully");
        } catch (IOException e) {
            throw new RuntimeException("Fail to store csv data: " + e.getMessage());
        }
    }

}
