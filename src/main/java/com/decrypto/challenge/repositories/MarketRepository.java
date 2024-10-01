package com.decrypto.challenge.repositories;

import com.decrypto.challenge.controllers.dtos.response.MarketInfo;
import com.decrypto.challenge.entities.Country;
import com.decrypto.challenge.entities.Market;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MarketRepository extends JpaRepository <Market,Long> {

    Optional<Market> findByCodeAndCountry(String code, String country);

    List<Market> findByCountry(String country);
}
