package com.decrypto.challenge.utils;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ErrorResponse {

    @Schema(description = "exception name", example = "ResourceNotFoundException")
    private String error;

    @Schema(description = "exception details", example = "client not found")
    private List<String> details;

    public ErrorResponse (String error, List<String> details) {
        super();
        this.error = error;
        this.details = details;
    }

}
