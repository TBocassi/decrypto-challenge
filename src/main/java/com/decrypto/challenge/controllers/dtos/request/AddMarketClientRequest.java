package com.decrypto.challenge.controllers.dtos.request;

import com.decrypto.challenge.utils.Messages;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddMarketClientRequest {

    @NotNull(message = Messages.CODE_NOT_NULL)
    @Pattern(regexp = "^[A-Z0-9]{2,4}$", message = Messages.CODE_BAD_FORMAT)
    @Schema(description = "market code", example = "MTEC")
    private String code;

    @NotNull(message = Messages.COUNTRY_NOT_NULL)
    @Pattern(regexp = "^[a-zA-Z]+$", message = Messages.COUNTRY_NOT_ALPHABETIC)
    @Schema(description = "country name", example = "Argentina")
    private String country;
}
