package com.poly.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Setter
@Getter
@Table(name = "orders")
public class Order extends AbstractEntity<Long> {

    @Column(name = "ma_don_hang", nullable = false, unique = true)
    private String maDonHang;

    @Column(name = "trang_thai", nullable = false)
    private String trangThai;

    @Column(name = "tong_tien", nullable = false)
    private Double tongTien;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_khach_hang", nullable = false)
    private User khachHang;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderDetail> chiTietDonHang;
}
