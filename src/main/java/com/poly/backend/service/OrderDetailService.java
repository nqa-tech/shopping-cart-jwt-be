package com.poly.backend.service;

import com.poly.backend.dto.OrderDetailDTO;
import com.poly.backend.entity.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    OrderDetailDTO getOrderDetailById(Long id);
    List<OrderDetailDTO> getAllOrderDetails();
    OrderDetailDTO createOrderDetail(OrderDetailDTO orderDetailDTO);
    OrderDetailDTO updateOrderDetail(Long id, OrderDetailDTO orderDetailDTO);
    void deleteOrderDetail(Long id);
    void saveAll(List<OrderDetailDTO> orderDetails);
}
