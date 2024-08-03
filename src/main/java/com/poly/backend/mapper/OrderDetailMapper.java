package com.poly.backend.mapper;

import com.poly.backend.dto.OrderDetailDTO;
import com.poly.backend.entity.OrderDetail;
import com.poly.backend.entity.Order;
import com.poly.backend.service.OrderService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;


@Mapper(componentModel = "spring", uses = {OrderService.class})
public abstract class OrderDetailMapper {
    @Lazy
    @Autowired
    protected OrderService orderService;

    @Mapping(source = "order.id", target = "orderId")
    public abstract OrderDetailDTO toDto(OrderDetail orderDetail);

    @Mapping(target = "order", source = "orderId", qualifiedByName = "mapOrder")
    public abstract OrderDetail toEntity(OrderDetailDTO orderDetailDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "order", source = "orderId", qualifiedByName = "mapOrder")
    public abstract void updateFromDto(OrderDetailDTO orderDetailDTO, @MappingTarget OrderDetail orderDetail);

    @Named("mapOrder")
    protected Order mapOrder(Long userId) {
        return orderService.findOrderById(userId).orElse(null);
    }
}
