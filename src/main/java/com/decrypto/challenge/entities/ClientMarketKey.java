package com.decrypto.challenge.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientMarketKey implements Serializable {
  
  private Long clientId;
  private Long marketId;
  
}
