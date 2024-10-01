package com.decrypto.challenge.controllers.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MarketInfo {

    private String marketCode;
    private BigDecimal percentage;
}
