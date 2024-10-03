package com.decrypto.challenge.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Market{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Schema(description = "market ID", example = "1")
  private Long id;

  @Schema(description = "market code", example = "MTEC")
  private String code;

  @Schema(description = "market description", example = "Mobile Technology")
  private String description;

  @Schema(description = "country name", example = "admin")
  private String country;

  @Schema(description = "audit field - created by", example = "admin")
  private String createdBy;

  @Schema(description = "audit field - created date", example = "2024-10-01T03:00:40.683152Z")
  private OffsetDateTime createdDate;

  @Schema(description = "audit field - created by", example = "admin")
  private String updatedBy;

  @Schema(description = "audit field - updated date", example = "2024-10-01T03:00:40.683152Z")
  private OffsetDateTime updatedDate;
  
}
