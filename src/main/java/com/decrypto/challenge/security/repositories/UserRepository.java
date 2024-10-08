package com.decrypto.challenge.security.repositories;

import com.decrypto.challenge.security.entities.SecurityUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<SecurityUser,Long> {

    Optional<SecurityUser> findByUsername(String username);
}

