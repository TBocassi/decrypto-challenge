package com.decrypto.challenge.repositories;

import com.decrypto.challenge.entities.ClientMarket;
import com.decrypto.challenge.entities.ClientMarketKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientMarketRepository extends JpaRepository<ClientMarket, ClientMarketKey> {}
