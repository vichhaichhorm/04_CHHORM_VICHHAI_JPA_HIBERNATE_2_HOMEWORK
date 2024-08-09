package com.vichhai.demo_data_jpa_2.apiResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class APIResponse<T> {
    private String message;
    private T payload;
    private HttpStatus code;
    private Integer status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime time;
}
