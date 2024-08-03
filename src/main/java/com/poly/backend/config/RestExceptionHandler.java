package com.poly.backend.config;

import com.poly.backend.dto.ErrorDTO;
import com.poly.backend.dto.response.Response;
import com.poly.backend.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@ControllerAdvice
@Slf4j
/**
 * Lớp RestExceptionHandler xử lý các ngoại lệ trong toàn bộ ứng dụng.
 * Nó bắt các ngoại lệ AppException và trả về phản hồi phù hợp với thông tin lỗi.
 */
public class RestExceptionHandler {

    @ExceptionHandler(value = {AppException.class})
    @ResponseBody
    /**
     * Phương thức xử lý ngoại lệ AppException
     * @param ex Ngoại lệ AppException
     * @return Đối tượng ResponseEntity chứa thông tin lỗi
     */
    public ResponseEntity<ErrorDTO> handleAppException(AppException ex) {
        // Ghi log ngoại lệ
        log.error("AppException: {}", ex.getMessage(), ex);

        // Tạo và trả về đối tượng ResponseEntity chứa thông tin lỗi
        return ResponseEntity
                .status(ex.getStatus())
                .body(ErrorDTO.builder().message(ex.getMessage()).build());
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    /**
     * Phương thức xử lý ngoại lệ chung
     * @param ex Ngoại lệ chung
     * @return Đối tượng ResponseEntity chứa thông tin lỗi
     */
    public ResponseEntity<Response> handleException(AppException ex) {
        // Ghi log ngoại lệ
        log.error("Exception: {}", ex.getMessage(), ex);

        // Tạo và trả về đối tượng ResponseEntity chứa thông tin lỗi
        return ResponseEntity
                .status(ex.getStatus()).body(
                        new Response(LocalDateTime.now(), null, ex.getMessage(), ex.getStatus().value()));
    }
}
