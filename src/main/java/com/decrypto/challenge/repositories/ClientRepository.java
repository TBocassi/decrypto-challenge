package com.decrypto.challenge.repositories;

import com.decrypto.challenge.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
