package com.decrypto.challenge.security.services;

import com.decrypto.challenge.security.controllers.dtos.UserRegistrationRequest;
import com.decrypto.challenge.security.entities.SecurityUser;

public interface UserService {

    public SecurityUser saveUser(UserRegistrationRequest userRegistrationRequest);

    public SecurityUser findByUsername(String username);

    public Void addAdminRole(Long userId);
}
