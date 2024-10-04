package com.decrypto.challenge.security.repositories;

import com.decrypto.challenge.security.entities.UserRoles;
import com.decrypto.challenge.security.entities.UserRolesKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRolesRepository extends JpaRepository<UserRoles, UserRolesKey> {

    List<UserRoles> findAllByUserId(Long clientId);

    List<UserRoles> findAllByRoleId(Long marketId);

}
