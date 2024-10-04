package com.decrypto.challenge.security.services.impl;

import com.decrypto.challenge.exceptions.InvalidCredentialsException;
import com.decrypto.challenge.security.components.JwtUtil;
import com.decrypto.challenge.security.controllers.dtos.AuthRequest;
import com.decrypto.challenge.security.services.AuthService;
import com.decrypto.challenge.utils.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;



    @Override
    public String aunthenticate(AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (Exception e) {
            throw new InvalidCredentialsException(Messages.INVALID_CREDENTIALS);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        return jwtUtil.generateToken(userDetails.getUsername(),userDetails.getAuthorities());
    }
}
