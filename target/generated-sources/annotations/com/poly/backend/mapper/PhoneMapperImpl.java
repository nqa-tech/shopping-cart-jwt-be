package com.poly.backend.mapper;

import com.poly.backend.dto.PhoneDTO;
import com.poly.backend.entity.Phone;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-03T11:40:49+0700",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class PhoneMapperImpl implements PhoneMapper {

    @Override
    public PhoneDTO toDto(Phone phone) {
        if ( phone == null ) {
            return null;
        }

        PhoneDTO.PhoneDTOBuilder phoneDTO = PhoneDTO.builder();

        phoneDTO.id( phone.getId() );
        phoneDTO.maSanPham( phone.getMaSanPham() );
        phoneDTO.tenSanPham( phone.getTenSanPham() );
        phoneDTO.hinhSanPham( phone.getHinhSanPham() );
        phoneDTO.gia( phone.getGia() );

        return phoneDTO.build();
    }

    @Override
    public List<PhoneDTO> toDto(List<Phone> phones) {
        if ( phones == null ) {
            return null;
        }

        List<PhoneDTO> list = new ArrayList<PhoneDTO>( phones.size() );
        for ( Phone phone : phones ) {
            list.add( toDto( phone ) );
        }

        return list;
    }

    @Override
    public Phone toEntity(PhoneDTO phoneDTO) {
        if ( phoneDTO == null ) {
            return null;
        }

        Phone.PhoneBuilder phone = Phone.builder();

        phone.maSanPham( phoneDTO.getMaSanPham() );
        phone.tenSanPham( phoneDTO.getTenSanPham() );
        phone.hinhSanPham( phoneDTO.getHinhSanPham() );
        phone.gia( phoneDTO.getGia() );

        return phone.build();
    }

    @Override
    public void updateFromDto(PhoneDTO phoneDTO, Phone phone) {
        if ( phoneDTO == null ) {
            return;
        }

        phone.setMaSanPham( phoneDTO.getMaSanPham() );
        phone.setTenSanPham( phoneDTO.getTenSanPham() );
        phone.setHinhSanPham( phoneDTO.getHinhSanPham() );
        phone.setGia( phoneDTO.getGia() );
    }
}
