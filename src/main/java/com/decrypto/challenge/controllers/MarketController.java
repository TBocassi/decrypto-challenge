package com.decrypto.challenge.controllers;

import com.decrypto.challenge.controllers.dtos.request.SaveMarketRequest;
import com.decrypto.challenge.controllers.dtos.request.UpdateMarketRequest;
import com.decrypto.challenge.controllers.dtos.response.MarketResponse;
import com.decrypto.challenge.entities.Market;
import com.decrypto.challenge.services.MarketService;
import com.decrypto.challenge.utils.ErrorResponse;
import com.decrypto.challenge.utils.Messages;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping(path = "/market")
public class MarketController {

  @Autowired
  private MarketService marketService;

  @Operation(summary = "Get all markets")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = Messages.OPENAPI_OK),
          @ApiResponse(responseCode = "500", description = Messages.OPENAPI_INTERNAL_ERROR , content = @Content (schema = @Schema (implementation = ErrorResponse.class))),
  })
  @GetMapping()
  private ResponseEntity<List<MarketResponse>> getAllMarkets() {
    return ResponseEntity.ok(marketService.getAllMarkets());
  }

  @Operation(summary = "Get market by ID")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = Messages.OPENAPI_OK),
          @ApiResponse(responseCode = "404", description = Messages.OPENAPI_NOT_FOUND ,content = @Content (schema = @Schema (implementation = ErrorResponse.class))),
          @ApiResponse(responseCode = "409", description = Messages.OPENAPI_NOT_HANDLED ,content = @Content (schema = @Schema (implementation = ErrorResponse.class))),
          @ApiResponse(responseCode = "500", description = Messages.OPENAPI_INTERNAL_ERROR , content = @Content (schema = @Schema (implementation = ErrorResponse.class))),
  })
  @GetMapping("/{marketId}")
  private ResponseEntity<Market> getMarketById(@PathVariable Long marketId) {
    return ResponseEntity.ok(marketService.getMarketById(marketId));
  }

  @Operation(summary = "Post a new market")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = Messages.OPENAPI_OK),
          @ApiResponse(responseCode = "400", description = Messages.OPENAPI_BAD_REQUEST ,content = @Content (schema = @Schema (implementation = ErrorResponse.class))),
          @ApiResponse(responseCode = "409", description = Messages.OPENAPI_NOT_HANDLED ,content = @Content (schema = @Schema (implementation = ErrorResponse.class))),
          @ApiResponse(responseCode = "500", description = Messages.OPENAPI_INTERNAL_ERROR , content = @Content (schema = @Schema (implementation = ErrorResponse.class))),
  })
  @PostMapping()
  private ResponseEntity<Market> postMarket(@Valid @RequestBody SaveMarketRequest saveMarketRequest) {
    return ResponseEntity.status(HttpStatus.CREATED).body(marketService.saveMarket(saveMarketRequest));
  }

  @Operation(summary = "Delete market by ID")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = Messages.OPENAPI_OK),
          @ApiResponse(responseCode = "404", description = Messages.OPENAPI_NOT_FOUND ,content = @Content (schema = @Schema (implementation = ErrorResponse.class))),
          @ApiResponse(responseCode = "409", description = Messages.OPENAPI_NOT_HANDLED ,content = @Content (schema = @Schema (implementation = ErrorResponse.class))),
          @ApiResponse(responseCode = "500", description = Messages.OPENAPI_INTERNAL_ERROR , content = @Content (schema = @Schema (implementation = ErrorResponse.class))),
  })
  @DeleteMapping("/{marketId}")
  private ResponseEntity<Long> deleteMarket(@PathVariable Long marketId) {
    return ResponseEntity.ok(marketService.deleteMarket(marketId));
  }

  @Operation(summary = "Update market by ID")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = Messages.OPENAPI_OK),
          @ApiResponse(responseCode = "404", description = Messages.OPENAPI_NOT_FOUND ,content = @Content (schema = @Schema (implementation = ErrorResponse.class))),
          @ApiResponse(responseCode = "400", description = Messages.OPENAPI_BAD_REQUEST ,content = @Content (schema = @Schema (implementation = ErrorResponse.class))),
          @ApiResponse(responseCode = "409", description = Messages.OPENAPI_NOT_HANDLED ,content = @Content (schema = @Schema (implementation = ErrorResponse.class))),
          @ApiResponse(responseCode = "500", description = Messages.OPENAPI_INTERNAL_ERROR , content = @Content (schema = @Schema (implementation = ErrorResponse.class))),
  })
  @PatchMapping("/{marketId}")
  private ResponseEntity<Market> updateMarket(@PathVariable Long marketId, @Valid @RequestBody UpdateMarketRequest updateMarketRequest) {
    return ResponseEntity.ok(marketService.updateMarket(marketId, updateMarketRequest));
  }
}
