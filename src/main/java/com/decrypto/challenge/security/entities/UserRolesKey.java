package com.decrypto.challenge.security.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRolesKey implements Serializable {

    private Long userId;
    private Long roleId;

}