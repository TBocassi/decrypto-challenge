package com.decrypto.challenge.repositories;

import com.decrypto.challenge.entities.Country;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country,Long> {
  
  Optional<Country> findByCountryName(String country);
}
