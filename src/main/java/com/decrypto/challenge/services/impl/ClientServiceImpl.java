package com.decrypto.challenge.services.impl;

import com.decrypto.challenge.controllers.dtos.request.SaveClientRequest;
import com.decrypto.challenge.controllers.dtos.request.UpdateClientRequest;
import com.decrypto.challenge.controllers.dtos.response.ClientResponse;
import com.decrypto.challenge.entities.Client;
import com.decrypto.challenge.exceptions.ResourceNotFoundException;
import com.decrypto.challenge.mappers.ClientMapper;
import com.decrypto.challenge.repositories.ClientRepository;
import com.decrypto.challenge.services.ClientService;
import com.decrypto.challenge.utils.Messages;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {
  
  @Autowired
  private ClientRepository clientRepository;
  
  @Override
  public List<ClientResponse> getAllClients() {
    return clientRepository.findAll().stream()
               .map(ClientMapper.INSTANCE::clientToClientResponse)
               .collect(Collectors.toList());
  }
  
  @Override
  public Client getClientById(final Long clientId) {
    return clientRepository.findById(clientId)
               .orElseThrow(() -> new ResourceNotFoundException(Messages.CLIENT_NOT_FOUND));
  }
  
  @Override
  public Client saveClient(final SaveClientRequest saveClientRequest) {
    Client client = ClientMapper.INSTANCE.saveClientRequestToClient(saveClientRequest);
    client.setCreatedBy("creator_user");
    client.setCreatedDate(OffsetDateTime.now(ZoneOffset.ofHours(-3)));
    return clientRepository.save(client);
  }
  
  @Override
  public Long deleteClient(final Long clientId) {
    
    Client client =clientRepository.findById(clientId)
                       .orElseThrow(() -> new ResourceNotFoundException(Messages.CLIENT_NOT_FOUND));
    clientRepository.delete(client);
    return client.getId();
  }
  
  @Override
  public Client updateClient(final Long clientId, final UpdateClientRequest updateClientRequest) {
    Client client =clientRepository.findById(clientId)
                       .orElseThrow(() -> new ResourceNotFoundException(Messages.CLIENT_NOT_FOUND));
    if (updateClientRequest.getDescription() != null){
      client.setDescription(updateClientRequest.getDescription());
    }
    client.setUpdatedBy("updater_user");
    client.setUpdatedDate(OffsetDateTime.now(ZoneOffset.ofHours(-3)));
    clientRepository.save(client);
    return client;
  }
  
}
