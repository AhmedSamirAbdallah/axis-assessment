package com.fintech.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApisResponse<T> {
    private T payload;
    private String message;
    private HttpStatus httpStatus;

    public static <T> ApisResponse<T> success(T payload, String message, HttpStatus httpStatus) {
        return ApisResponse.<T>builder()
                .payload(payload)
                .message(message)
                .httpStatus(httpStatus)
                .build();
    }

    public static <T> ApisResponse<T> error(T payload, HttpStatus httpStatus) {
        return ApisResponse.<T>builder()
                .payload(payload)
                .message(null)
                .httpStatus(httpStatus)
                .build();
    }

}