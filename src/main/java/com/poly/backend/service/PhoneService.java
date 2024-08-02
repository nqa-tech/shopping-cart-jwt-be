package com.poly.backend.service;

import com.poly.backend.dto.PhoneDTO;

import java.util.List;

public interface PhoneService {
    PhoneDTO getPhoneById(Long id);

    List<PhoneDTO> getAllPhones();

    void addPhone(PhoneDTO phoneDTO);

    void updatePhone(long id, PhoneDTO phoneDTO);

    void deletePhone(long id);
}
