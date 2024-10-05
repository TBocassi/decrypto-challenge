package com.decrypto.challenge.security.services.impl;

import com.decrypto.challenge.security.entities.SecurityRole;
import com.decrypto.challenge.security.entities.SecurityUser;
import com.decrypto.challenge.security.entities.UserRoles;
import com.decrypto.challenge.security.repositories.RoleRepository;
import com.decrypto.challenge.security.repositories.UserRepository;
import com.decrypto.challenge.security.repositories.UserRolesRepository;
import com.decrypto.challenge.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.*;
import java.util.stream.Collectors;


public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRolesRepository userRolesRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SecurityUser securityUser = userService.findByUsername(username);
        return new org.springframework.security.core.userdetails.User(
                securityUser.getUsername(),
                securityUser.getPassword(),
                mapRolesToAuthorities(getRoles(securityUser))
        );
    }

    private List<SecurityRole> getRoles(SecurityUser username) {
        List<SecurityRole> securityRoleList = new ArrayList<>();
        List<UserRoles> userRolesList = userRolesRepository.findAllByUserId(username.getId());
        for (UserRoles userRoles : userRolesList){
            securityRoleList.add(roleRepository.findById(userRoles.getRoleId()).get());
        }
        return securityRoleList;
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<SecurityRole> securityRoles) {
        return securityRoles.stream()
                .map(securityRole -> new SimpleGrantedAuthority(securityRole.getName()))
                .collect(Collectors.toList());
    }


}

