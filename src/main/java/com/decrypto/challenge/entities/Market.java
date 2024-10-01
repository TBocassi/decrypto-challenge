package com.decrypto.challenge.entities;

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
  private Long id;
  
  private String code;
  
  private String description;
  
  private String country;
  
  private String createdBy;
  
  private OffsetDateTime createdDate;
  
  private String updatedBy;
  
  private OffsetDateTime updatedDate;
  
}
