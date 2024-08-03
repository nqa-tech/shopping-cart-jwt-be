package com.poly.backend.service.impl;

import com.poly.backend.dto.OrderDetailDTO;
import com.poly.backend.entity.OrderDetail;
import com.poly.backend.exception.AppException;
import com.poly.backend.mapper.OrderDetailMapper;
import com.poly.backend.repository.OrderDetailRepository;
import com.poly.backend.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;
    private final OrderDetailMapper orderDetailMapper;

    @Override
    public OrderDetailDTO getOrderDetailById(Long id) {
        Optional<OrderDetail> optionalOrderDetail = orderDetailRepository.findById(id);
        if (optionalOrderDetail.isPresent()) {
            return orderDetailMapper.toDto(optionalOrderDetail.get());
        } else {
            throw new AppException("OrderDetail not found", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<OrderDetailDTO> getAllOrderDetails() {
        List<OrderDetail> orderDetails = orderDetailRepository.findAll();
        return orderDetails.stream()
                .map(orderDetailMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDetailDTO createOrderDetail(OrderDetailDTO orderDetailDTO) {
        OrderDetail orderDetail = orderDetailMapper.toEntity(orderDetailDTO);
        OrderDetail savedOrderDetail = orderDetailRepository.save(orderDetail);
        return orderDetailMapper.toDto(savedOrderDetail);
    }

    @Override
    public OrderDetailDTO updateOrderDetail(Long id, OrderDetailDTO orderDetailDTO) {
        Optional<OrderDetail> optionalOrderDetail = orderDetailRepository.findById(id);
        if (optionalOrderDetail.isPresent()) {
            OrderDetail orderDetail = optionalOrderDetail.get();
            orderDetailMapper.updateFromDto(orderDetailDTO, orderDetail);
            OrderDetail updatedOrderDetail = orderDetailRepository.save(orderDetail);
            return orderDetailMapper.toDto(updatedOrderDetail);
        } else {
            throw new AppException("OrderDetail not found", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteOrderDetail(Long id) {
        if (orderDetailRepository.existsById(id)) {
            orderDetailRepository.deleteById(id);
        } else {
            throw new AppException("OrderDetail not found", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void saveAll(List<OrderDetailDTO> orderDetails) {
        List<OrderDetail> tranfer = orderDetails.stream()
                .map(orderDetailMapper::toEntity)
                .collect(Collectors.toList());
        orderDetailRepository.saveAll(tranfer);
    }
}
