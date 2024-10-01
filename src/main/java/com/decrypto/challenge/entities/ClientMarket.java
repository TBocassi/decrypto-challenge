package com.decrypto.challenge.entities;

import io.swagger.v3.oas.annotations.media.Schema;
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
  @Schema(description = "client ID", example = "1")
  private Long clientId;
  
  @Id
  @Column(name = "market_id")
  @Schema(description = "market ID", example = "1")
  private Long marketId;

}
