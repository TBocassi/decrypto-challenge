package com.decrypto.challenge.security.controllers.dtos;

import com.decrypto.challenge.utils.Messages;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

    @NotNull(message = Messages.USERNAME_NOT_NULL)
    @Size(min = 3 , message = Messages.USERNAME_MIN_CHAR)
    @Schema(description = "username", example = "admin123")
    private String username;

    @NotNull(message = Messages.PASSWORD_NOT_NULL)
    @Size(min = 3 , message = Messages.PASSWORD_MIN_CHAR)
    @Schema(description = "password", example = "password123")
    private String password;

}
