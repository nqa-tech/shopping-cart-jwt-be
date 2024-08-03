package com.poly.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailDTO {
    private Long id;

    @NotNull(message = "Order ID không được để trống")
    private Long orderId;

    @NotNull(message = "Mã sản phẩm không được để trống")
    private Long maSanPham;

    @NotBlank(message = "Tên sản phẩm không được để trống")
    private String tenSanPham;

    @NotNull(message = "Giá sản phẩm không được để trống")
    private Double giaSanPham;

    @NotNull(message = "Số lượng không được để trống")
    private Integer soLuong;
}
