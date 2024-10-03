package com.decrypto.challenge.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Country {
  
  @Id
  @Schema(description = "country ID", example = "1")
  private Long id;

  @Schema(description = "country name", example = "Argentina")
  private String countryName;
}
