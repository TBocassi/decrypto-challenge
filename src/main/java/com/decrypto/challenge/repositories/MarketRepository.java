package com.decrypto.challenge.repositories;

import com.decrypto.challenge.entities.Market;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketRepository extends JpaRepository <Market,Long> {}
