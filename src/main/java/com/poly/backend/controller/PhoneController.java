package com.poly.backend.controller;

import com.poly.backend.dto.PhoneDTO;
import com.poly.backend.dto.response.Response;
import com.poly.backend.exception.AppException;
import com.poly.backend.service.PhoneService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/phones")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PhoneController {

    final PhoneService phoneService;
    static final Logger logger = LoggerFactory.getLogger(PhoneController.class);

    /**
     * Endpoint: GET /api/phones
     * Chức năng: Lấy danh sách tất cả sản phẩm điện thoại.
     * Xử lý: Gọi phoneService.getAllPhones() để lấy danh sách sản phẩm điện thoại từ dịch vụ.
     * Phản hồi: Trả về danh sách sản phẩm điện thoại và thông điệp thành công nếu không có lỗi, hoặc phản hồi lỗi nếu có lỗi.
     */
    @GetMapping
    public List<PhoneDTO> getAllPhones() {
        try {
            return phoneService.getAllPhones();
        } catch (AppException e) {
            logger.error("Lỗi khi lấy toàn bộ danh sách sản phẩm điện thoại: {}", e.getMessage());
            return null;
        }
    }

    /**
     * Endpoint: GET /api/phones/{id}
     * Chức năng: Lấy thông tin của một sản phẩm điện thoại theo ID.
     * Xử lý: Gọi phoneService.getPhoneById(id) để lấy thông tin của sản phẩm điện thoại.
     * Phản hồi: Trả về thông tin của sản phẩm điện thoại nếu tồn tại, hoặc phản hồi lỗi nếu không tìm thấy sản phẩm điện thoại.
     */
    @GetMapping("/{id}")
    public PhoneDTO getPhoneById(@PathVariable long id) {
        try {
            return phoneService.getPhoneById(id);
        } catch (AppException e) {
            logger.error("Lỗi khi lấy thông tin sản phẩm điện thoại, ID: {}: {}", id, e.getMessage());
            return null;
        }
    }

    /**
     * Endpoint: POST /api/phones
     * Chức năng: Thêm một sản phẩm điện thoại mới.
     * Xử lý: Gọi phoneService.addPhone(phoneDTO) để thêm sản phẩm điện thoại mới.
     * Phản hồi: Trả về thông tin của sản phẩm điện thoại đã được thêm và thông báo thành công, hoặc phản hồi lỗi nếu có lỗi.
     */
    @PostMapping
    public ResponseEntity<Response> addPhone(@Valid @RequestBody PhoneDTO phoneDTO) {
        try {
            PhoneDTO createdPhone = phoneService.addPhone(phoneDTO);
            logger.info("Thêm sản phẩm điện thoại thành công");
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new Response(LocalDateTime.now(), createdPhone, "Thêm sản phẩm điện thoại thành công", 201)
            );
        } catch (AppException ex) {
            logger.error("Lỗi khi thêm sản phẩm điện thoại: {}", ex.getMessage());
            return ResponseEntity.status(ex.getStatus()).body(
                    new Response(LocalDateTime.now(), null, ex.getMessage(), ex.getStatus().value())
            );
        }
    }

    /**
     * Endpoint: PUT /api/phones/{id}
     * Chức năng: Cập nhật thông tin của một sản phẩm điện thoại.
     * Xử lý: Gọi phoneService.updatePhone(id, phoneDTO) để cập nhật thông tin sản phẩm điện thoại.
     * Phản hồi: Trả về thông tin của sản phẩm điện thoại sau khi cập nhật và thông báo thành công, hoặc phản hồi lỗi nếu không tìm thấy sản phẩm điện thoại để cập nhật.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Response> updatePhone(@PathVariable long id, @Valid @RequestBody PhoneDTO phoneDTO) {
        try {
            PhoneDTO updatedPhone = phoneService.updatePhone(id, phoneDTO);
            logger.info("Cập nhật thông tin sản phẩm điện thoại thành công, ID: {}", id);
            return ResponseEntity.ok(
                    new Response(LocalDateTime.now(), updatedPhone, "Cập nhật thông tin sản phẩm điện thoại thành công", 200)
            );
        } catch (AppException ex) {
            logger.error("Lỗi khi cập nhật thông tin sản phẩm điện thoại, ID: {}: {}", id, ex.getMessage());
            return ResponseEntity.status(ex.getStatus()).body(
                    new Response(LocalDateTime.now(), null, ex.getMessage(), ex.getStatus().value())
            );
        }
    }

    /**
     * Endpoint: DELETE /api/phones/{id}
     * Chức năng: Xóa một sản phẩm điện thoại.
     * Xử lý: Gọi phoneService.deletePhone(id) để xóa sản phẩm điện thoại.
     * Phản hồi: Trả về thông báo thành công nếu xóa thành công, hoặc phản hồi lỗi nếu không tìm thấy sản phẩm điện thoại để xóa.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deletePhone(@PathVariable long id) {
        try {
            phoneService.deletePhone(id);
            logger.info("Xóa sản phẩm điện thoại thành công, ID: {}", id);
            return ResponseEntity.ok(
                    new Response(LocalDateTime.now(), null, "Xóa thông tin sản phẩm điện thoại thành công", 200)
            );
        } catch (AppException ex) {
            logger.error("Lỗi khi xóa sản phẩm điện thoại, ID: {}: {}", id, ex.getMessage());
            return ResponseEntity.status(ex.getStatus()).body(
                    new Response(LocalDateTime.now(), null, ex.getMessage(), ex.getStatus().value())
            );
        }
    }
}
