package it.engineering.service.impl;

import it.engineering.dto.ServiceTypeDto;
import it.engineering.entity.ServiceType;
import it.engineering.exception.ResourceNotFoundException;
import it.engineering.mapper.ServiceTypeMapper;
import it.engineering.repository.ServiceTypeRepository;
import it.engineering.service.ServiceTypeService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
