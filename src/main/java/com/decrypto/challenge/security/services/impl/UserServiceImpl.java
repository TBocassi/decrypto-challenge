package com.decrypto.challenge.security.services.impl;

import com.decrypto.challenge.entities.Client;
import com.decrypto.challenge.entities.ClientMarket;
import com.decrypto.challenge.exceptions.DuplicateClientMarketException;
import com.decrypto.challenge.exceptions.DuplicateUserException;
import com.decrypto.challenge.exceptions.ResourceNotFoundException;
import com.decrypto.challenge.security.controllers.dtos.UserRegistrationRequest;
import com.decrypto.challenge.security.entities.SecurityUser;
import com.decrypto.challenge.security.entities.UserRoles;
import com.decrypto.challenge.security.repositories.RoleRepository;
import com.decrypto.challenge.security.repositories.UserRepository;
import com.decrypto.challenge.security.repositories.UserRolesRepository;
import com.decrypto.challenge.security.services.UserService;
import com.decrypto.challenge.utils.Constants;
import com.decrypto.challenge.utils.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRolesRepository userRolesRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public SecurityUser saveUser(UserRegistrationRequest userRegistrationRequest) {

        Optional<SecurityUser> optionalSecurityUser = userRepository.findByUsername(userRegistrationRequest.getUsername());
        if (optionalSecurityUser.isPresent()) {
            throw new DuplicateUserException(Messages.DUPLICATED_USER + userRegistrationRequest.getUsername());
        }

        SecurityUser securityUser = new SecurityUser();
        securityUser.setPassword(passwordEncoder.encode(userRegistrationRequest.getPassword()));
        securityUser.setUsername(userRegistrationRequest.getUsername());

        securityUser = userRepository.save(securityUser);

        UserRoles userRoles = new UserRoles(securityUser.getId(), Constants.USER_ROLE_ID);
        userRoles = userRolesRepository.save(userRoles);
        return securityUser;
    }

    public SecurityUser findByUsername(String username) {
       return  userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException(Messages.USER_NOT_FOUND + username));
    }

    public Void addAdminRole(Long userId) {
        SecurityUser securityUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(Messages.USER_NOT_FOUND + userId));
        userRolesRepository.save(UserRoles.builder().roleId(Constants.ADMIN_ROLE_ID).userId(userId).build());
        return null;
    }
}