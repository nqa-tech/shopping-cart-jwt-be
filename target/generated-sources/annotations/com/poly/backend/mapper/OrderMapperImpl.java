package com.poly.backend.mapper;

import com.poly.backend.dto.OrderDTO;
import com.poly.backend.entity.Order;
import com.poly.backend.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-04T02:06:03+0700",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class OrderMapperImpl extends OrderMapper {

    @Override
    public OrderDTO toDto(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDTO.OrderDTOBuilder orderDTO = OrderDTO.builder();

        orderDTO.maKhachHang( orderKhachHangId( order ) );
        orderDTO.id( order.getId() );
        orderDTO.maDonHang( order.getMaDonHang() );
        orderDTO.trangThai( order.getTrangThai() );
        orderDTO.tongTien( order.getTongTien() );

        return orderDTO.build();
    }

    @Override
    public Order toEntity(OrderDTO orderDTO) {
        if ( orderDTO == null ) {
            return null;
        }

        Order.OrderBuilder order = Order.builder();

        order.khachHang( mapUser( orderDTO.getMaKhachHang() ) );
        order.maDonHang( orderDTO.getMaDonHang() );
        order.trangThai( orderDTO.getTrangThai() );
        order.tongTien( orderDTO.getTongTien() );

        return order.build();
    }

    @Override
    public void updateFromDto(OrderDTO orderDTO, Order order) {
        if ( orderDTO == null ) {
            return;
        }

        order.setKhachHang( mapUser( orderDTO.getMaKhachHang() ) );
        order.setMaDonHang( orderDTO.getMaDonHang() );
        order.setTrangThai( orderDTO.getTrangThai() );
        order.setTongTien( orderDTO.getTongTien() );
    }

    private Long orderKhachHangId(Order order) {
        if ( order == null ) {
            return null;
        }
        User khachHang = order.getKhachHang();
        if ( khachHang == null ) {
            return null;
        }
        Long id = khachHang.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
