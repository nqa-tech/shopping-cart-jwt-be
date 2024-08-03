package com.poly.backend.mapper;

import com.poly.backend.dto.OrderDTO;
import com.poly.backend.entity.Order;
import com.poly.backend.entity.User;
import com.poly.backend.service.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {UserService.class})
public abstract class OrderMapper {
    @Autowired
    protected UserService userService;


    @Mapping(source = "khachHang.id", target = "maKhachHang")
    public abstract OrderDTO toDto(Order order);

    @Mapping(target = "khachHang", source = "maKhachHang", qualifiedByName = "mapUser")
    public abstract Order toEntity(OrderDTO orderDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "khachHang", source = "maKhachHang", qualifiedByName = "mapUser")
    public abstract void updateFromDto(OrderDTO orderDTO, @MappingTarget Order order);

    @Named("mapUser")
    protected User mapUser(Long userId) {
        return userService.findUserById(userId).orElse(null);
    }
}

