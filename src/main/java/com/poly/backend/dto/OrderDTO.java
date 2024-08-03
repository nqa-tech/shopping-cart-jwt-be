package com.poly.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO {
    private Long id;

    @NotBlank(message = "Mã đơn hàng không được để trống")
    private String maDonHang;

    @NotBlank(message = "Trạng thái không được để trống")
    private String trangThai;

    @NotNull(message = "Tổng tiền không được để trống")
    private Double tongTien;

    @NotNull(message = "Mã khách hàng không được để trống")
    private Long maKhachHang;

    private List<OrderDetailDTO> items;
}
