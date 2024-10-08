package com.decrypto.challenge.services.impl;

import com.decrypto.challenge.controllers.dtos.request.SaveMarketRequest;
import com.decrypto.challenge.controllers.dtos.request.UpdateMarketRequest;
import com.decrypto.challenge.controllers.dtos.response.MarketResponse;
import com.decrypto.challenge.entities.ClientMarket;
import com.decrypto.challenge.entities.Country;
import com.decrypto.challenge.entities.Market;
import com.decrypto.challenge.exceptions.CountryNotAllowedException;
import com.decrypto.challenge.exceptions.DuplicateMarketException;
import com.decrypto.challenge.exceptions.ResourceNotFoundException;
import com.decrypto.challenge.mappers.MarketMapper;
import com.decrypto.challenge.repositories.ClientMarketRepository;
import com.decrypto.challenge.repositories.CountryRepository;
import com.decrypto.challenge.repositories.MarketRepository;
import com.decrypto.challenge.security.components.JwtUtil;
import com.decrypto.challenge.services.MarketService;
import com.decrypto.challenge.utils.Messages;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class MarketServiceImpl implements MarketService {
  
  @Autowired
  private MarketRepository marketRepository;
  
  @Autowired
  private CountryRepository countryRepository;

  @Autowired
  private ClientMarketRepository clientMarketRepository;

  @Autowired
  private JwtUtil jwtUtil;

  @Override
  public List<MarketResponse> getAllMarkets() {
    return marketRepository.findAll().stream()
               .map(MarketMapper.INSTANCE::marketToMarketResponse)
               .collect(Collectors.toList());
  }
  
  @Override
  public Market getMarketById(final Long marketId) {
    return marketRepository.findById(marketId)
               .orElseThrow(() -> new ResourceNotFoundException(Messages.MARKET_NOT_FOUND + marketId));
  }
  
  @Override
  public Market saveMarket(final SaveMarketRequest saveMarketRequest, String token) {
    Optional<Market> optionalMarket = marketRepository.findByCodeAndCountry(saveMarketRequest.getCode(),saveMarketRequest.getCountry());
    if (optionalMarket.isPresent()){
      throw new DuplicateMarketException(Messages.DUPLICATED_MARKET + optionalMarket.get().getCode() + " " + optionalMarket.get().getCountry()) ;
    }
    if (isAllowedCountry(saveMarketRequest.getCountry())){
      
      Market market = MarketMapper.INSTANCE.saveMarketRequestToMarket(saveMarketRequest);
      market.setCreatedBy(jwtUtil.extractUsername(token.substring(7)));
      market.setCreatedDate(OffsetDateTime.now(ZoneOffset.ofHours(-3)));
      return marketRepository.save(market);
      
    } else throw new CountryNotAllowedException(Messages.COUNTRY_NOT_ALLOWED + saveMarketRequest.getCountry());
    
  }
  
  
  @Override
  public Long deleteMarket(final Long marketId) {
    
    Market market =marketRepository.findById(marketId)
                       .orElseThrow(() -> new ResourceNotFoundException(Messages.MARKET_NOT_FOUND + marketId));
    marketRepository.delete(market);
    List<ClientMarket> clientMarkets = clientMarketRepository.findAllByMarketId(marketId);
    for (ClientMarket clientMarket :clientMarkets){
      clientMarketRepository.delete(clientMarket);
    }
    return market.getId();
  }
  
  @Override
  public Market updateMarket(final Long marketId, final UpdateMarketRequest updateMarketRequest,String token) {
    Market market =marketRepository.findById(marketId)
                       .orElseThrow(() -> new ResourceNotFoundException(Messages.MARKET_NOT_FOUND + marketId));
    if (updateMarketRequest.getDescription() != null){
      market.setDescription(updateMarketRequest.getDescription());
    }
  
    if (updateMarketRequest.getCode() != null){
      market.setCode(updateMarketRequest.getCode());
    }
  
    if (updateMarketRequest.getCountry() != null){
      if (isAllowedCountry(updateMarketRequest.getCountry())){
        market.setCountry(updateMarketRequest.getCountry());
      } else throw new CountryNotAllowedException(Messages.COUNTRY_NOT_ALLOWED + updateMarketRequest.getCountry());
      market.setCountry(updateMarketRequest.getCountry());
    }
    
    market.setUpdatedBy(jwtUtil.extractUsername(token.substring(7)));
    market.setUpdatedDate(OffsetDateTime.now(ZoneOffset.ofHours(-3)));
    marketRepository.save(market);
    return market;
  }
  
  private boolean isAllowedCountry(final String country) {
    Optional<Country> optionalCountry = countryRepository.findByCountryName(country);
    if (optionalCountry.isPresent()){
      return true;
    } else return false;
  }
  
}
