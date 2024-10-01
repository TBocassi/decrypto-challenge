package com.decrypto.challenge.controllers;

import com.decrypto.challenge.controllers.dtos.request.SaveClientRequest;
import com.decrypto.challenge.controllers.dtos.request.SaveMarketRequest;
import com.decrypto.challenge.controllers.dtos.request.UpdateClientRequest;
import com.decrypto.challenge.controllers.dtos.request.UpdateMarketRequest;
import com.decrypto.challenge.controllers.dtos.response.ClientResponse;
import com.decrypto.challenge.controllers.dtos.response.MarketResponse;
import com.decrypto.challenge.entities.Client;
import com.decrypto.challenge.entities.Market;
import com.decrypto.challenge.services.ClientService;
import com.decrypto.challenge.services.MarketService;
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
@RequestMapping(path = "/market")
public class MarketController {
  
  @Autowired
  private MarketService marketService;
  
  @GetMapping()
  private ResponseEntity<List<MarketResponse>> getAllClients() {
    return ResponseEntity.ok(marketService.getAllMarkets());
  }
  
  @GetMapping("/{marketId}")
  private ResponseEntity<Market> getMarketById (@PathVariable Long marketId){
    return ResponseEntity.ok(marketService.getMarketById(marketId));
  }
  
  @PostMapping()
  private ResponseEntity<Market> postMarket(@Valid @RequestBody SaveMarketRequest saveMarketRequest) {
    return ResponseEntity.status(HttpStatus.CREATED).body(marketService.saveMarket(saveMarketRequest));
  }
  
  @DeleteMapping("/{marketId}")
  private ResponseEntity<Long> deleteMarket (@PathVariable Long marketId){
    return ResponseEntity.ok(marketService.deleteMarket(marketId));
  }
  
  @PatchMapping("/{marketId}")
  private ResponseEntity<Market> updateClient (@PathVariable Long marketId ,@Valid @RequestBody
                                                                                UpdateMarketRequest updateMarketRequest){
    return ResponseEntity.ok(marketService.updateMarket(marketId , updateMarketRequest));
  }
  
}
