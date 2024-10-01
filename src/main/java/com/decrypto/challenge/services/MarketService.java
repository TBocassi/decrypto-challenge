package com.decrypto.challenge.services;

import com.decrypto.challenge.controllers.dtos.request.SaveClientRequest;
import com.decrypto.challenge.controllers.dtos.request.SaveMarketRequest;
import com.decrypto.challenge.controllers.dtos.request.UpdateClientRequest;
import com.decrypto.challenge.controllers.dtos.request.UpdateMarketRequest;
import com.decrypto.challenge.controllers.dtos.response.MarketResponse;
import com.decrypto.challenge.entities.Market;
import java.util.List;

public interface MarketService {
  
  List<MarketResponse> getAllMarkets();
  
  Market getMarketById(Long marketId);
  
  Market saveMarket(SaveMarketRequest saveMarketRequest);
  
  Long deleteMarket(Long marketId);
  
  Market updateMarket(Long marketId, UpdateMarketRequest updateMarketRequest);
}
