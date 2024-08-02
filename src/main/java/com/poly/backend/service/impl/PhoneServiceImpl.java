package com.poly.backend.service.impl;

import com.poly.backend.dto.PhoneDTO;
import com.poly.backend.entity.Phone;
import com.poly.backend.exception.AppException;
import com.poly.backend.mapper.PhoneMapper;
import com.poly.backend.repository.PhoneRepository;
import com.poly.backend.service.PhoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PhoneServiceImpl implements PhoneService {

    @Autowired
    private final PhoneRepository phoneRepository;
    @Autowired
    private final PhoneMapper phoneMapper;

    @Override
    public PhoneDTO getPhoneById(Long id) {
        Optional<Phone> optionalPhone = phoneRepository.findById(id);
        return optionalPhone.map(phoneMapper::toDto).orElse(null);
    }

    @Override
    public List<PhoneDTO> getAllPhones() {
        List<Phone> phones = phoneRepository.findAll();
        return phoneMapper.toDto(phones);
    }

    @Override
    public void addPhone(PhoneDTO phoneDTO) {
        validatePhone(phoneDTO);
        Phone phone = phoneMapper.toEntity(phoneDTO);
        phoneRepository.save(phone);
    }

    @Override
    public void updatePhone(long id, PhoneDTO phoneDTO) {
        validatePhone(phoneDTO);
        Optional<Phone> optionalPhone = phoneRepository.findById(id);
        if (optionalPhone.isPresent()) {
            Phone phone = optionalPhone.get();
            phoneMapper.updateFromDto(phoneDTO, phone);
            phoneRepository.save(phone);
        }
    }

    @Override
    public void deletePhone(long id) {
        phoneRepository.deleteById(id);
    }

    private void validatePhone(PhoneDTO phoneDTO) {
        if (phoneDTO.getMaSanPham() == null || phoneDTO.getMaSanPham().isEmpty()) {
            throw new AppException("Mã sản phẩm không được để trống", HttpStatus.BAD_REQUEST);
        }
        if (phoneDTO.getTenSanPham() == null || phoneDTO.getTenSanPham().isEmpty()) {
            throw new AppException("Tên sản phẩm không được để trống", HttpStatus.BAD_REQUEST);
        }
        if (phoneDTO.getHinhSanPham() == null || phoneDTO.getHinhSanPham().isEmpty()) {
            throw new AppException("Hình sản phẩm không được để trống", HttpStatus.BAD_REQUEST);
        }
        if (phoneDTO.getGia() == null) {
            throw new AppException("Giá không được để trống", HttpStatus.BAD_REQUEST);
        }
    }
}
