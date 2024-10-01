package com.decrypto.challenge.mappers;

import com.decrypto.challenge.controllers.dtos.request.SaveMarketRequest;
import com.decrypto.challenge.controllers.dtos.response.MarketResponse;
import com.decrypto.challenge.entities.Market;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MarketMapper {
  
  MarketMapper INSTANCE = Mappers.getMapper(MarketMapper.class);
  
  MarketResponse marketToMarketResponse(Market market);
  
  Market saveMarketRequestToMarket(SaveMarketRequest saveMarketRequest);
}
