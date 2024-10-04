package com.decrypto.challenge.security.entities;

import com.decrypto.challenge.entities.ClientMarketKey;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "user_roles")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(UserRolesKey.class)
public class UserRoles {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Id
    @Column(name = "role_id")
    private Long roleId;

}
