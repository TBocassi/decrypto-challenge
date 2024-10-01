package com.decrypto.challenge.services.impl;

import com.decrypto.challenge.controllers.dtos.request.AddMarketClientRequest;
import com.decrypto.challenge.controllers.dtos.request.SaveClientRequest;
import com.decrypto.challenge.controllers.dtos.request.UpdateClientRequest;
import com.decrypto.challenge.controllers.dtos.response.ClientResponse;
import com.decrypto.challenge.controllers.dtos.response.CountryStats;
import com.decrypto.challenge.controllers.dtos.response.MarketInfo;
import com.decrypto.challenge.controllers.dtos.response.StatsResponse;
import com.decrypto.challenge.entities.*;
import com.decrypto.challenge.exceptions.DuplicateClientException;
import com.decrypto.challenge.exceptions.DuplicateClientMarketException;
import com.decrypto.challenge.exceptions.ResourceNotFoundException;
import com.decrypto.challenge.mappers.ClientMapper;
import com.decrypto.challenge.repositories.ClientMarketRepository;
import com.decrypto.challenge.repositories.ClientRepository;
import com.decrypto.challenge.repositories.CountryRepository;
import com.decrypto.challenge.repositories.MarketRepository;
import com.decrypto.challenge.services.ClientService;
import com.decrypto.challenge.utils.Messages;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {
  
  @Autowired
  private ClientRepository clientRepository;

  @Autowired
  private MarketRepository marketRepository;

  @Autowired
  private ClientMarketRepository clientMarketRepository;

  @Autowired
  private CountryRepository countryRepository;
  
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
   Optional<Client> optionalClient = clientRepository.findByDescription(saveClientRequest.getDescription());
   if (optionalClient.isEmpty()) {
     Client client = ClientMapper.INSTANCE.saveClientRequestToClient(saveClientRequest);
     client.setCreatedBy("creator_user");
     client.setCreatedDate(OffsetDateTime.now(ZoneOffset.ofHours(-3)));
     return clientRepository.save(client);
   } else throw new DuplicateClientException(Messages.DUPLICATED_CLIENT + saveClientRequest.getDescription());
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

  @Override
  public Void addMarketClient(Long clientId, AddMarketClientRequest addMarketClientRequest) {
    Client client = clientRepository.findById(clientId)
            .orElseThrow(() -> new ResourceNotFoundException(Messages.CLIENT_NOT_FOUND));
    Market market = marketRepository.findByCodeAndCountry(addMarketClientRequest.getCode(),addMarketClientRequest.getCountry())
            .orElseThrow(() -> new ResourceNotFoundException(Messages.MARKET_NOT_FOUND));
    ClientMarketKey key = new ClientMarketKey(client.getId(), market.getId());
    Optional<ClientMarket> optionalClientMarket = clientMarketRepository.findById(key);
    if (optionalClientMarket.isPresent()){
      throw new DuplicateClientMarketException(Messages.DUPLICATED_CLIENT_MARKET);
    } else {
    ClientMarket clientMarket = ClientMarket.builder().clientId(client.getId()).marketId(market.getId()).build();
    clientMarketRepository.save(clientMarket);
    return null;}
  }

  @Override
  public StatsResponse getStats() {
    StatsResponse statsResponse = new StatsResponse(new ArrayList<>());
    List<Country> countryList = countryRepository.findAll();
    for(Country country : countryList){
      CountryStats countryStats = new CountryStats();
      countryStats.setCountry(country.getCountryName());
      List<MarketInfo> marketInfoList = new ArrayList<>();
      List<Market> marketList = marketRepository.findByCountry(country.getCountryName());
      for (Market market : marketList){
        MarketInfo marketInfo = new MarketInfo();
        marketInfo.setMarketCode(market.getCode());
        marketInfo.setPercentage(getPercentage(market));
        marketInfoList.add(marketInfo);
      }
      countryStats.setMarketInfos(marketInfoList);
      statsResponse.getCountryStats().add(countryStats);
    }
    return statsResponse;
  }

  private BigDecimal getPercentage(Market market) {

    BigDecimal totalClientMarket = new BigDecimal(clientMarketRepository.count()) ;
    BigDecimal actualMarketCount = new BigDecimal(clientMarketRepository.countByMarketId(market.getId()));
    BigDecimal percentage = actualMarketCount
            .multiply(BigDecimal.valueOf(100))
            .divide(totalClientMarket, 2, RoundingMode.HALF_UP);
    return percentage;
  }

}
