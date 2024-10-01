package com.decrypto.challenge.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ErrorResponse {

    private String error;
    private List<String> details;

    public ErrorResponse (String error, List<String> details) {
        super();
        this.error = error;
        this.details = details;
    }

}
