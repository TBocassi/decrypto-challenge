package com.decrypto.challenge.controllers.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponse {
  
  private Long id;
  
  private String description;
}
