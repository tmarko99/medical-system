package it.engineering.mapper;

import it.engineering.dto.ServiceTypeDto;
import it.engineering.entity.ServiceType;
import org.mapstruct.Mapper;

@Mapper
public interface ServiceTypeMapper {
    ServiceType toEntity(ServiceTypeDto serviceTypeDto);

    ServiceTypeDto toDto(ServiceType serviceType);
}
