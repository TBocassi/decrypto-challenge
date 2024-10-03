package com.decrypto.challenge.services;

import com.decrypto.challenge.controllers.dtos.request.AddMarketClientRequest;
import com.decrypto.challenge.controllers.dtos.request.SaveClientRequest;
import com.decrypto.challenge.controllers.dtos.request.UpdateClientRequest;
import com.decrypto.challenge.controllers.dtos.response.ClientResponse;
import com.decrypto.challenge.controllers.dtos.response.StatsResponse;
import com.decrypto.challenge.entities.Client;
import java.util.List;

public interface ClientService {
  
  List<ClientResponse> getAllClients();
  
  Client getClientById(Long clientId);
  
  Client saveClient(SaveClientRequest saveClientRequest);
  
  Long deleteClient(Long clientId);
  
  Client updateClient(Long clientId, UpdateClientRequest updateClientRequest);

  Void addMarketClient(Long clientId, AddMarketClientRequest addMarketClientRequest);

  StatsResponse getStats();
}
