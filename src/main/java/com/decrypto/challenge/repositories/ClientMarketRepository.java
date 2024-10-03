package com.decrypto.challenge.repositories;

import com.decrypto.challenge.entities.ClientMarket;
import com.decrypto.challenge.entities.ClientMarketKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientMarketRepository extends JpaRepository<ClientMarket, ClientMarketKey> {

    Long countByMarketId(Long id);

    List<ClientMarket> findAllByClientId(Long clientId);

    List<ClientMarket> findAllByMarketId(Long marketId);
}
