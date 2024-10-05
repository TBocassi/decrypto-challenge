package com.decrypto.challenge.security.controllers;
import com.decrypto.challenge.security.controllers.dtos.UserRegistrationRequest;
import com.decrypto.challenge.security.entities.SecurityUser;
import com.decrypto.challenge.security.services.UserService;

import com.decrypto.challenge.utils.ErrorResponse;
import com.decrypto.challenge.utils.Messages;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Create new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = Messages.OPENAPI_OK),
            @ApiResponse(responseCode = "400", description = Messages.OPENAPI_BAD_REQUEST ,content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = Messages.OPENAPI_NOT_HANDLED ,content = @Content (schema = @Schema (implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = Messages.OPENAPI_INTERNAL_ERROR , content = @Content (schema = @Schema (implementation = ErrorResponse.class))),
    })
    @PostMapping("/register")
    public ResponseEntity<SecurityUser> registerUser(@RequestBody @Valid UserRegistrationRequest registrationRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(registrationRequest));
    }

    @Operation(summary = "Add admin role to user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = Messages.OPENAPI_OK),
            @ApiResponse(responseCode = "400", description = Messages.OPENAPI_BAD_REQUEST ,content = @Content (schema = @Schema (implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = Messages.OPENAPI_FORBIDDEN ),
            @ApiResponse(responseCode = "409", description = Messages.OPENAPI_NOT_HANDLED ,content = @Content (schema = @Schema (implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = Messages.OPENAPI_INTERNAL_ERROR , content = @Content (schema = @Schema (implementation = ErrorResponse.class))),
    })
    @PostMapping("/{userId}/add-admin")
    public ResponseEntity<Void> addAdminRole(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.addAdminRole(userId));
    }
}