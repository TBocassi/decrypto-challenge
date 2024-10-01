package com.decrypto.challenge.utils;

import com.decrypto.challenge.entities.Country;
import com.decrypto.challenge.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
  
  @Autowired
  private CountryRepository countryRepository;
  
  public void run(String... args) throws Exception {
    if (countryRepository.count() == 0) {
      Country countryArg = Country.builder().id(1L).countryName("Argentina").build();
      Country countryUru = Country.builder().id(2L).countryName("Uruguay").build();
      countryRepository.save(countryArg);
      countryRepository.save(countryUru);
    }
  }
}
