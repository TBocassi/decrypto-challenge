package com.decrypto.challenge.mappers;

import com.decrypto.challenge.controllers.dtos.request.SaveClientRequest;
import com.decrypto.challenge.controllers.dtos.response.ClientResponse;
import com.decrypto.challenge.entities.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClientMapper {
  
  ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);
  
  ClientResponse clientToClientResponse(Client client);
  
  Client saveClientRequestToClient(SaveClientRequest saveClientRequest);
  
}
