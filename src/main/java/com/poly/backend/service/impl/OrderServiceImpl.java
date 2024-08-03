package com.poly.backend.service.impl;

import com.poly.backend.dto.OrderDTO;
import com.poly.backend.dto.OrderDetailDTO;
import com.poly.backend.entity.Order;
import com.poly.backend.entity.OrderDetail;
import com.poly.backend.exception.AppException;
import com.poly.backend.mapper.OrderMapper;
import com.poly.backend.repository.OrderRepository;
import com.poly.backend.service.OrderDetailService;
import com.poly.backend.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderDetailService orderDetailService;

    @Override
    public OrderDTO getOrderById(Long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.getChiTietDonHang().size(); // Load items
            return orderMapper.toDto(order);
        } else {
            throw new AppException("Order not found", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = orderMapper.toEntity(orderDTO);
        Order savedOrder = orderRepository.save(order);
        List<OrderDetailDTO> orderDetailDTOS = orderDTO.getItems();
        for (OrderDetailDTO orderDetailDTO : orderDetailDTOS) {
            orderDetailDTO.setOrderId(savedOrder.getId());
            System.out.println(orderDetailDTO);
            orderDetailService.createOrderDetail(orderDetailDTO);
        }

        return orderMapper.toDto(savedOrder);
    }

    @Override
    public OrderDTO updateOrder(Long id, OrderDTO orderDTO) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            orderMapper.updateFromDto(orderDTO, order);
            Order updatedOrder = orderRepository.save(order);
            return orderMapper.toDto(updatedOrder);
        } else {
            throw new AppException("Order not found", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
        } else {
            throw new AppException("Order not found", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Order> findOrderById(@Lazy Long id) {
        return orderRepository.findById(id);
    }
}
