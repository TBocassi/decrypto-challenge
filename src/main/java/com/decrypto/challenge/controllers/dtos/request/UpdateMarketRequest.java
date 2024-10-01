package com.decrypto.challenge.controllers.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMarketRequest {
  
  private String code;
  
  private String description;
  
  private String country;
}
