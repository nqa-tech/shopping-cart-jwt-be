package com.poly.backend.service;

import com.poly.backend.dto.PhoneDTO;
import com.poly.backend.entity.Order;
import com.poly.backend.entity.Phone;

import java.util.List;
import java.util.Optional;

public interface PhoneService {
    PhoneDTO getPhoneById(Long id);

    List<PhoneDTO> getAllPhones();

    PhoneDTO addPhone(PhoneDTO phoneDTO);

    PhoneDTO updatePhone(long id, PhoneDTO phoneDTO);

    void deletePhone(long id);

    Optional<Phone> findPhoneById(Long id);
}
