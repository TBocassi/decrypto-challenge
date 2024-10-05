package com.decrypto.challenge.security.services;

import com.decrypto.challenge.security.controllers.dtos.AuthRequest;

public interface AuthService {

    String aunthenticate(AuthRequest authRequest);
}
