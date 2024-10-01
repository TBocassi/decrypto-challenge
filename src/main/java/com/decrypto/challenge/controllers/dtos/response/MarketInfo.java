package com.decrypto.challenge.controllers.dtos.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MarketInfo {

    @Schema(description = "market code",example = "MTEC")
    private String marketCode;

    @Schema(description = "percentage of clients by country and market" , example = "43.65")
    private BigDecimal percentage;
}
