package com.decrypto.challenge.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@IdClass(ClientMarketKey.class)
@Table(name = "client_market")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientMarket{
  
  @Id
  @Column(name = "client_id")
  private Long clientId;
  
  @Id
  @Column(name = "market_id")
  private Long marketId;

}
