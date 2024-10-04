package com.decrypto.challenge.security.repositories;

import com.decrypto.challenge.security.entities.SecurityRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<SecurityRole,Long> {

    SecurityRole findByName(String name);
}
