package com.decrypto.challenge.utils;

import com.decrypto.challenge.entities.Country;
import com.decrypto.challenge.repositories.CountryRepository;
import com.decrypto.challenge.security.entities.SecurityRole;
import com.decrypto.challenge.security.entities.SecurityUser;
import com.decrypto.challenge.security.entities.UserRoles;
import com.decrypto.challenge.security.repositories.RoleRepository;
import com.decrypto.challenge.security.repositories.UserRepository;
import com.decrypto.challenge.security.repositories.UserRolesRepository;
import com.decrypto.challenge.security.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
  
  @Autowired
  private CountryRepository countryRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserRolesRepository userRolesRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public void run(String... args) throws Exception {
    if (countryRepository.count() == 0) {
      Country countryArg = Country.builder().countryName(Constants.COUNTRY_NAME_ARGENTINA).build();
      Country countryUru = Country.builder().countryName(Constants.COUNTRY_NAME_URUGUAY).build();
      countryRepository.save(countryArg);
      countryRepository.save(countryUru);
    }
    if (roleRepository.count() == 0) {
      SecurityRole adminRole = SecurityRole.builder().name(Constants.ADMIN_ROLE_NAME).build();
      SecurityRole userRole = SecurityRole.builder().name(Constants.USER_ROLE_NAME).build();
      roleRepository.save(adminRole);
      roleRepository.save(userRole);
    }
    if (userRepository.count() == 0){
      SecurityUser securityUser = SecurityUser.builder().username(Constants.ADMIN_STRING).password(passwordEncoder.encode(Constants.ADMIN_STRING)).build();
      securityUser = userRepository.save(securityUser);

      userRolesRepository.save(UserRoles.builder().userId(securityUser.getId()).roleId(Constants.ADMIN_ROLE_ID).build());
      userRolesRepository.save(UserRoles.builder().userId(securityUser.getId()).roleId(Constants.USER_ROLE_ID).build());
    }
  }
}
