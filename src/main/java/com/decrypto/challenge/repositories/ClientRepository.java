package com.decrypto.challenge.repositories;

import com.decrypto.challenge.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByDescription(String description);
}
