package com.decrypto.challenge.controllers.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarketResponse {
  
  private Long id;
  
  private String code;
  
  private String description;
  
  private String country;
}
