package com.decrypto.challenge.controllers.dtos.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponse {

  @Schema(description = "client ID", example = "1")
  private Long id;

  @Schema(description = "client description", example = "Samsung")
  private String description;
}
