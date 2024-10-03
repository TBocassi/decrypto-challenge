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
public class Client {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Schema(description = "client ID", example = "Samsung")
  private Long id;

  @Schema(description = "client description", example = "Samsung")
  private String description;

  @Schema(description = "audit field - created by", example = "admin")
  private String createdBy;

  @Schema(description = "audit field - created date", example = "2024-10-01T03:00:40.683152Z")
  private OffsetDateTime createdDate;

  @Schema(description = "audit field - created by", example = "admin")
  private String updatedBy;

  @Schema(description = "audit field - updated date", example = "2024-10-01T03:00:40.683152Z")
  private OffsetDateTime updatedDate;
}
