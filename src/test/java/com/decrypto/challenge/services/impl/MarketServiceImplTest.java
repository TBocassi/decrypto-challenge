package com.decrypto.challenge.services.impl;

import com.decrypto.challenge.entities.ClientMarket;
import com.decrypto.challenge.repositories.ClientMarketRepository;
import com.decrypto.challenge.security.components.JwtUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.decrypto.challenge.controllers.dtos.request.SaveMarketRequest;
import com.decrypto.challenge.controllers.dtos.request.UpdateMarketRequest;
import com.decrypto.challenge.controllers.dtos.response.MarketResponse;
import com.decrypto.challenge.entities.Country;
import com.decrypto.challenge.entities.Market;
import com.decrypto.challenge.exceptions.CountryNotAllowedException;
import com.decrypto.challenge.exceptions.ResourceNotFoundException;
import com.decrypto.challenge.mappers.MarketMapper;
import com.decrypto.challenge.repositories.CountryRepository;
import com.decrypto.challenge.repositories.MarketRepository;
import com.decrypto.challenge.utils.Messages;
import org.junit.jupiter.api.BeforeEach;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MarketServiceImplTest {

    @InjectMocks
    private MarketServiceImpl marketService;

    @Mock
    private MarketRepository marketRepository;

    @Mock
    private CountryRepository countryRepository;

    @Mock
    private ClientMarketRepository clientMarketRepository;

    @Mock
    private MarketMapper marketMapper;
    @Mock
    private JwtUtil jwtUtil;

    private Market market;
    private ClientMarket clientMarket;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        market = new Market();
        market.setId(1L);
        market.setDescription("Test Market");
        market.setCountry("Test Country");

        clientMarket = new ClientMarket();
        clientMarket.setMarketId(1l);
        clientMarket.setClientId(1l);
    }

    @Test
    void getAllMarkets() {
        when(marketRepository.findAll()).thenReturn(Arrays.asList(market));

        List<MarketResponse> response = marketService.getAllMarkets();

        assertNotNull(response);
        assertEquals(1, response.size());
        verify(marketRepository, times(1)).findAll();
    }

    @Test
    void getMarketById_Success() {
        when(marketRepository.findById(1L)).thenReturn(Optional.of(market));

        Market result = marketService.getMarketById(1L);

        assertNotNull(result);
        assertEquals(market.getId(), result.getId());
        verify(marketRepository, times(1)).findById(1L);
    }

    @Test
    void getMarketById_NotFound() {
        when(marketRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            marketService.getMarketById(1L);
        });

        assertEquals(Messages.MARKET_NOT_FOUND + 1L, exception.getMessage());
    }

    @Test
    void saveMarket_Success() {
        SaveMarketRequest request = new SaveMarketRequest();
        request.setCountry("Test Country");
        request.setDescription("test description");
        request.setCode("TEST");

        Country allowedCountry = new Country();
        allowedCountry.setCountryName("Test Country");
        allowedCountry.setId(1L);

        when(jwtUtil.extractUsername(anyString())).thenReturn("username");
        when(countryRepository.findByCountryName("Test Country")).thenReturn(Optional.of(allowedCountry));
        when(marketMapper.saveMarketRequestToMarket(request)).thenReturn(market);
        when(marketRepository.save(any(Market.class))).thenReturn(market);

        Market result = marketService.saveMarket(request,"test-token");

        assertNotNull(result);
        assertEquals(market.getId(), result.getId());
        verify(marketRepository, times(1)).save(any(Market.class));
    }

    @Test
    void saveMarket_CountryNotAllowed() {
        SaveMarketRequest request = new SaveMarketRequest();
        request.setCountry("Invalid Country");

        when(countryRepository.findByCountryName("Invalid Country")).thenReturn(Optional.empty());

        Exception exception = assertThrows(CountryNotAllowedException.class, () -> {
            marketService.saveMarket(request,"test-token");
        });

        assertEquals(Messages.COUNTRY_NOT_ALLOWED + "Invalid Country", exception.getMessage());
    }

    @Test
    void deleteMarket_Success() {
        when(marketRepository.findById(1L)).thenReturn(Optional.of(market));
        when(clientMarketRepository.findAllByMarketId(anyLong())).thenReturn(List.of(clientMarket));

        Long deletedId = marketService.deleteMarket(1L);

        assertEquals(market.getId(), deletedId);
        verify(marketRepository, times(1)).delete(market);
    }

    @Test
    void deleteMarket_NotFound() {
        when(marketRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            marketService.deleteMarket(1L);
        });

        assertEquals(Messages.MARKET_NOT_FOUND + 1L, exception.getMessage());
    }

    @Test
    void updateMarket_Success() {
        UpdateMarketRequest updateRequest = new UpdateMarketRequest();
        updateRequest.setDescription("Updated Description");
        updateRequest.setCountry("Test Country");
        updateRequest.setCode("TEST");

        when(marketRepository.findById(1L)).thenReturn(Optional.of(market));
        when(countryRepository.findByCountryName("Test Country")).thenReturn(Optional.of(new Country()));
        when(marketRepository.save(any(Market.class))).thenReturn(market);
        when(jwtUtil.extractUsername(anyString())).thenReturn("username");

        Market updatedMarket = marketService.updateMarket(1L, updateRequest,"test-token");

        assertNotNull(updatedMarket);
        assertEquals("Updated Description", updatedMarket.getDescription());
        verify(marketRepository, times(1)).save(market);
    }

    @Test
    void updateMarket_NotFound() {
        when(marketRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            marketService.updateMarket(1L, new UpdateMarketRequest(),anyString());
        });

        assertEquals(Messages.MARKET_NOT_FOUND + 1L, exception.getMessage());
    }

    @Test
    void updateMarket_CountryNotAllowed() {
        when(marketRepository.findById(1L)).thenReturn(Optional.of(market));
        UpdateMarketRequest updateRequest = new UpdateMarketRequest();
        updateRequest.setCountry("Invalid Country");

        when(countryRepository.findByCountryName("Invalid Country")).thenReturn(Optional.empty());

        Exception exception = assertThrows(CountryNotAllowedException.class, () -> {
            marketService.updateMarket(1L, updateRequest,anyString());
        });

        assertEquals(Messages.COUNTRY_NOT_ALLOWED + "Invalid Country", exception.getMessage());
    }
}
