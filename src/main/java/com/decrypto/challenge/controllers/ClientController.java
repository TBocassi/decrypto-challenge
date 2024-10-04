package com.decrypto.challenge.controllers;

import com.decrypto.challenge.controllers.dtos.request.AddMarketClientRequest;
import com.decrypto.challenge.controllers.dtos.request.SaveClientRequest;
import com.decrypto.challenge.controllers.dtos.request.UpdateClientRequest;
import com.decrypto.challenge.controllers.dtos.response.ClientResponse;
import com.decrypto.challenge.controllers.dtos.response.StatsResponse;
import com.decrypto.challenge.entities.Client;
import com.decrypto.challenge.services.ClientService;
import com.decrypto.challenge.utils.ErrorResponse;
import com.decrypto.challenge.utils.Messages;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping (path = "/client")
public class ClientController {
  
  @Autowired
  private ClientService clientService;

  @Operation(summary = "Get all clients")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = Messages.OPENAPI_OK),
          @ApiResponse(responseCode = "403", description = Messages.OPENAPI_FORBIDDEN ),
          @ApiResponse(responseCode = "500", description = Messages.OPENAPI_INTERNAL_ERROR , content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
  })
  @GetMapping()
  private ResponseEntity<List<ClientResponse>> getAllClients() {
    return ResponseEntity.ok(clientService.getAllClients());
  }

  @Operation(summary = "Get client by ID")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = Messages.OPENAPI_OK),
          @ApiResponse(responseCode = "403", description = Messages.OPENAPI_FORBIDDEN ),
          @ApiResponse(responseCode = "404", description = Messages.OPENAPI_NOT_FOUND ,content = @Content (schema = @Schema (implementation = ErrorResponse.class))),
          @ApiResponse(responseCode = "409", description = Messages.OPENAPI_NOT_HANDLED ,content = @Content (schema = @Schema (implementation = ErrorResponse.class))),
          @ApiResponse(responseCode = "500", description = Messages.OPENAPI_INTERNAL_ERROR , content = @Content (schema = @Schema (implementation = ErrorResponse.class))),
  })
  @GetMapping("/{clientId}")
  private ResponseEntity<Client> getClientById (@PathVariable Long clientId){
    return ResponseEntity.ok(clientService.getClientById(clientId));
  }

  @Operation(summary = "Post a new client")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = Messages.OPENAPI_OK),
          @ApiResponse(responseCode = "400", description = Messages.OPENAPI_BAD_REQUEST ,content = @Content (schema = @Schema (implementation = ErrorResponse.class))),
          @ApiResponse(responseCode = "403", description = Messages.OPENAPI_FORBIDDEN ),
          @ApiResponse(responseCode = "409", description = Messages.OPENAPI_NOT_HANDLED ,content = @Content (schema = @Schema (implementation = ErrorResponse.class))),
          @ApiResponse(responseCode = "500", description = Messages.OPENAPI_INTERNAL_ERROR , content = @Content (schema = @Schema (implementation = ErrorResponse.class))),
  })
  @PostMapping()
  private ResponseEntity<Client> postClient(@RequestHeader(HttpHeaders.AUTHORIZATION) String token , @Valid @RequestBody SaveClientRequest saveClientRequest) {
    return ResponseEntity.status(HttpStatus.CREATED).body(clientService.saveClient(saveClientRequest,token));
  }

  @Operation(summary = "Delete client by ID")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = Messages.OPENAPI_OK),
          @ApiResponse(responseCode = "403", description = Messages.OPENAPI_FORBIDDEN ),
          @ApiResponse(responseCode = "404", description = Messages.OPENAPI_NOT_FOUND ,content = @Content (schema = @Schema (implementation = ErrorResponse.class))),
          @ApiResponse(responseCode = "409", description = Messages.OPENAPI_NOT_HANDLED ,content = @Content (schema = @Schema (implementation = ErrorResponse.class))),
          @ApiResponse(responseCode = "500", description = Messages.OPENAPI_INTERNAL_ERROR , content = @Content (schema = @Schema (implementation = ErrorResponse.class))),
  })
  @DeleteMapping("/{clientId}")
  private ResponseEntity<Long> deleteClient (@PathVariable Long clientId){
    return ResponseEntity.ok(clientService.deleteClient(clientId));
  }

  @Operation(summary = "Update client by ID")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = Messages.OPENAPI_OK),
          @ApiResponse(responseCode = "403", description = Messages.OPENAPI_FORBIDDEN ),
          @ApiResponse(responseCode = "404", description = Messages.OPENAPI_NOT_FOUND ,content = @Content (schema = @Schema (implementation = ErrorResponse.class))),
          @ApiResponse(responseCode = "400", description = Messages.OPENAPI_BAD_REQUEST ,content = @Content (schema = @Schema (implementation = ErrorResponse.class))),
          @ApiResponse(responseCode = "409", description = Messages.OPENAPI_NOT_HANDLED ,content = @Content (schema = @Schema (implementation = ErrorResponse.class))),
          @ApiResponse(responseCode = "500", description = Messages.OPENAPI_INTERNAL_ERROR , content = @Content (schema = @Schema (implementation = ErrorResponse.class))),
  })
  @PatchMapping("/{clientId}")
  private ResponseEntity<Client> updateClient (@RequestHeader(HttpHeaders.AUTHORIZATION) String token ,@PathVariable Long clientId ,@Valid @RequestBody UpdateClientRequest updateClientRequest){
    return ResponseEntity.ok(clientService.updateClient(clientId , updateClientRequest,token));
  }

  @Operation(summary = "Create relation between market and client")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = Messages.OPENAPI_OK),
          @ApiResponse(responseCode = "403", description = Messages.OPENAPI_FORBIDDEN ),
          @ApiResponse(responseCode = "404", description = Messages.OPENAPI_NOT_FOUND ,content = @Content (schema = @Schema (implementation = ErrorResponse.class))),
          @ApiResponse(responseCode = "400", description = Messages.OPENAPI_BAD_REQUEST ,content = @Content (schema = @Schema (implementation = ErrorResponse.class))),
          @ApiResponse(responseCode = "409", description = Messages.OPENAPI_NOT_HANDLED ,content = @Content (schema = @Schema (implementation = ErrorResponse.class))),
          @ApiResponse(responseCode = "500", description = Messages.OPENAPI_INTERNAL_ERROR , content = @Content (schema = @Schema (implementation = ErrorResponse.class))),
  })
  @PostMapping("/{clientId}")
  private ResponseEntity<Void> addMarketToClient (@PathVariable Long clientId , @RequestBody AddMarketClientRequest addMarketClientRequest){
    return ResponseEntity.ok(clientService.addMarketClient(clientId , addMarketClientRequest));
  }

  @Operation(summary = "Get totalizing distribution figures of clients by country and market.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = Messages.OPENAPI_OK),
          @ApiResponse(responseCode = "403", description = Messages.OPENAPI_FORBIDDEN ),
          @ApiResponse(responseCode = "404", description = Messages.OPENAPI_NOT_FOUND ,content = @Content (schema = @Schema (implementation = ErrorResponse.class))),
          @ApiResponse(responseCode = "400", description = Messages.OPENAPI_BAD_REQUEST ,content = @Content (schema = @Schema (implementation = ErrorResponse.class))),
          @ApiResponse(responseCode = "409", description = Messages.OPENAPI_NOT_HANDLED ,content = @Content (schema = @Schema (implementation = ErrorResponse.class))),
          @ApiResponse(responseCode = "500", description = Messages.OPENAPI_INTERNAL_ERROR , content = @Content (schema = @Schema (implementation = ErrorResponse.class))),
  })
  @GetMapping("/stats")
  private ResponseEntity<StatsResponse> getStats() {
    return ResponseEntity.ok(clientService.getStats());
  }
}
