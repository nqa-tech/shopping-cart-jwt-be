package com.poly.backend.service;

import com.poly.backend.dto.OrderDTO;
import com.poly.backend.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    OrderDTO getOrderById(Long id);
    List<OrderDTO> getAllOrders();
    OrderDTO createOrder(OrderDTO orderDTO);
    OrderDTO updateOrder(Long id, OrderDTO orderDTO);
    void deleteOrder(Long id);
    Optional<Order> findOrderById(Long id);
}
