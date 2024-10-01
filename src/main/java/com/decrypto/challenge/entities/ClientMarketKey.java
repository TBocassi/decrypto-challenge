package com.decrypto.challenge.entities;

import java.io.Serializable;
import lombok.Data;

@Data
public class ClientMarketKey implements Serializable {
  
  private Long clientId;
  private Long marketId;
  
}
