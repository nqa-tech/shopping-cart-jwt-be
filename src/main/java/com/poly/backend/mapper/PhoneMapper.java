package com.poly.backend.mapper;

import com.poly.backend.dto.PhoneDTO;
import com.poly.backend.entity.Phone;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PhoneMapper {

    PhoneMapper INSTANCE = Mappers.getMapper(PhoneMapper.class);

    PhoneDTO toDto(Phone phone);
    List<PhoneDTO> toDto(List<Phone> phones);
    Phone toEntity(PhoneDTO phoneDTO);

    @Mapping(target = "id", ignore = true)
    void updateFromDto(PhoneDTO phoneDTO, @MappingTarget Phone phone);
}
