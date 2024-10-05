package com.decrypto.challenge.security.controllers;

import com.decrypto.challenge.security.controllers.dtos.AuthRequest;
import com.decrypto.challenge.security.services.AuthService;
import com.decrypto.challenge.utils.ErrorResponse;
import com.decrypto.challenge.utils.Messages;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    AuthService authService;

    @Operation(summary = "Authenticate user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = Messages.OPENAPI_OK),
            @ApiResponse(responseCode = "400", description = Messages.OPENAPI_BAD_REQUEST ,content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = Messages.OPENAPI_NOT_HANDLED ,content = @Content (schema = @Schema (implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = Messages.OPENAPI_INTERNAL_ERROR , content = @Content (schema = @Schema (implementation = ErrorResponse.class))),
    })
    @PostMapping("/authenticate")
    public ResponseEntity<String> registerUser(@RequestBody @Valid AuthRequest authRequest) {
        return ResponseEntity.ok().body(authService.aunthenticate(authRequest));
    }
}