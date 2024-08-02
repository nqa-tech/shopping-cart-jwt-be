package com.poly.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Setter
@Getter
@Table(name = "phone")
public class Phone extends AbstractEntity<Long> {

    @Column(name = "ma_san_pham")
    private String maSanPham;

    @Column(name = "ten_san_pham")
    private String tenSanPham;

    @Column(name = "hinh_san_pham")
    private String hinhSanPham;

    @Column(name = "gia")
    private Double gia;
}
