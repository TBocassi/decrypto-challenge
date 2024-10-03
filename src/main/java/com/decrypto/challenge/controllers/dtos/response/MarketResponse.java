package com.decrypto.challenge.controllers.dtos.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarketResponse {

  @Schema(description = "market ID", example = "1")
  private Long id;

  @Schema(description = "market code", example = "MTEC")
  private String code;

  @Schema(description = "market description", example = "Mobile Technology")
  private String description;

  @Schema(description = "country name", example = "admin")
  private String country;
}
