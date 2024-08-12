package com.poly.backend.mapper;

import com.poly.backend.dto.OrderDetailDTO;
import com.poly.backend.entity.Order;
import com.poly.backend.entity.OrderDetail;
import com.poly.backend.entity.Phone;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-05T08:24:29+0700",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class OrderDetailMapperImpl extends OrderDetailMapper {

    @Override
    public OrderDetailDTO toDto(OrderDetail orderDetail) {
        if ( orderDetail == null ) {
            return null;
        }

        OrderDetailDTO.OrderDetailDTOBuilder orderDetailDTO = OrderDetailDTO.builder();

        orderDetailDTO.orderId( orderDetailOrderId( orderDetail ) );
        orderDetailDTO.maSanPham( orderDetailMaSanPhamId( orderDetail ) );
        orderDetailDTO.id( orderDetail.getId() );
        orderDetailDTO.tenSanPham( orderDetail.getTenSanPham() );
        orderDetailDTO.giaSanPham( orderDetail.getGiaSanPham() );
        orderDetailDTO.soLuong( orderDetail.getSoLuong() );

        return orderDetailDTO.build();
    }

    @Override
    public OrderDetail toEntity(OrderDetailDTO orderDetailDTO) {
        if ( orderDetailDTO == null ) {
            return null;
        }

        OrderDetail.OrderDetailBuilder orderDetail = OrderDetail.builder();

        orderDetail.order( mapOrder( orderDetailDTO.getOrderId() ) );
        orderDetail.maSanPham( mapPhone( orderDetailDTO.getMaSanPham() ) );
        orderDetail.tenSanPham( orderDetailDTO.getTenSanPham() );
        orderDetail.giaSanPham( orderDetailDTO.getGiaSanPham() );
        orderDetail.soLuong( orderDetailDTO.getSoLuong() );

        return orderDetail.build();
    }

    @Override
    public void updateFromDto(OrderDetailDTO orderDetailDTO, OrderDetail orderDetail) {
        if ( orderDetailDTO == null ) {
            return;
        }

        orderDetail.setOrder( mapOrder( orderDetailDTO.getOrderId() ) );
        orderDetail.setMaSanPham( mapPhone( orderDetailDTO.getMaSanPham() ) );
        orderDetail.setTenSanPham( orderDetailDTO.getTenSanPham() );
        orderDetail.setGiaSanPham( orderDetailDTO.getGiaSanPham() );
        orderDetail.setSoLuong( orderDetailDTO.getSoLuong() );
    }

    private Long orderDetailOrderId(OrderDetail orderDetail) {
        if ( orderDetail == null ) {
            return null;
        }
        Order order = orderDetail.getOrder();
        if ( order == null ) {
            return null;
        }
        Long id = order.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long orderDetailMaSanPhamId(OrderDetail orderDetail) {
        if ( orderDetail == null ) {
            return null;
        }
        Phone maSanPham = orderDetail.getMaSanPham();
        if ( maSanPham == null ) {
            return null;
        }
        Long id = maSanPham.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
