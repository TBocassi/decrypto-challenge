package com.decrypto.challenge.controllers;

import com.decrypto.challenge.controllers.dtos.request.SaveClientRequest;
import com.decrypto.challenge.controllers.dtos.request.UpdateClientRequest;
import com.decrypto.challenge.controllers.dtos.response.ClientResponse;
import com.decrypto.challenge.entities.Client;
import com.decrypto.challenge.services.ClientService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (path = "/client")
public class ClientController {
  
  @Autowired
  private ClientService clientService;
  
  @GetMapping()
  private ResponseEntity<List<ClientResponse>> getAllClients() {
    return ResponseEntity.ok(clientService.getAllClients());
  }
  
  @GetMapping("/{clientId}")
  private ResponseEntity<Client> getClientById (@PathVariable Long clientId){
    return ResponseEntity.ok(clientService.getClientById(clientId));
  }
  
  @PostMapping()
  private ResponseEntity<Client> postClient(@Valid @RequestBody SaveClientRequest saveClientRequest) {
    return ResponseEntity.status(HttpStatus.CREATED).body(clientService.saveClient(saveClientRequest));
  }
  
  @DeleteMapping("/{clientId}")
  private ResponseEntity<Long> deleteClient (@PathVariable Long clientId){
    return ResponseEntity.ok(clientService.deleteClient(clientId));
  }
  
  @PatchMapping("/{clientId}")
  private ResponseEntity<Client> updateClient (@PathVariable Long clientId ,@Valid @RequestBody UpdateClientRequest updateClientRequest){
    return ResponseEntity.ok(clientService.updateClient(clientId , updateClientRequest));
  }
}
