package com.decrypto.challenge.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import lombok.Data;

@Data
@Entity
@IdClass(ClientMarketKey.class)
@Table(name = "client_market")
public class ClientMarket{
  
  @Id
  @Column(name = "client_id")
  private Long clientId;
  
  @Id
  @Column(name = "market_id")
  private Long marketId;
  
  private String createdBy;
  
  private OffsetDateTime createdDate;
  
  private String updatedBy;
  
  private OffsetDateTime updatedDate;
  
}
