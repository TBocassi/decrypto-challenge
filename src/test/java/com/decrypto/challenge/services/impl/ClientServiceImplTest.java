package com.decrypto.challenge.services.impl;

import com.decrypto.challenge.controllers.dtos.request.AddMarketClientRequest;
import com.decrypto.challenge.controllers.dtos.request.SaveClientRequest;
import com.decrypto.challenge.controllers.dtos.request.UpdateClientRequest;
import com.decrypto.challenge.controllers.dtos.response.ClientResponse;
import com.decrypto.challenge.controllers.dtos.response.CountryStats;
import com.decrypto.challenge.controllers.dtos.response.MarketInfo;
import com.decrypto.challenge.controllers.dtos.response.StatsResponse;
import com.decrypto.challenge.entities.Client;
import com.decrypto.challenge.entities.ClientMarket;
import com.decrypto.challenge.entities.Country;
import com.decrypto.challenge.entities.Market;
import com.decrypto.challenge.exceptions.DuplicateClientException;
import com.decrypto.challenge.exceptions.DuplicateClientMarketException;
import com.decrypto.challenge.exceptions.ResourceNotFoundException;
import com.decrypto.challenge.mappers.ClientMapper;
import com.decrypto.challenge.repositories.ClientMarketRepository;
import com.decrypto.challenge.repositories.ClientRepository;
import com.decrypto.challenge.repositories.CountryRepository;
import com.decrypto.challenge.repositories.MarketRepository;
import com.decrypto.challenge.utils.Messages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ClientServiceImplTest {

    @InjectMocks
    private ClientServiceImpl clientService;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private MarketRepository marketRepository;

    @Mock
    private ClientMarketRepository clientMarketRepository;

    @Mock
    private CountryRepository countryRepository;

    @Mock
    private ClientMapper clientMapper;

    private Client client;
    private SaveClientRequest saveClientRequest;
    private UpdateClientRequest updateClientRequest;
    private AddMarketClientRequest addMarketClientRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        client = new Client();
        client.setId(1L);
        client.setDescription("Test Client");

        saveClientRequest = new SaveClientRequest();
        saveClientRequest.setDescription("Test Client");

        updateClientRequest = new UpdateClientRequest();
        updateClientRequest.setDescription("Updated Client");

        addMarketClientRequest = new AddMarketClientRequest();
        addMarketClientRequest.setCode("CODE");
        addMarketClientRequest.setCountry("Test Country");
    }

    @Test
    void getAllClients_Success() {
        when(clientRepository.findAll()).thenReturn(Collections.singletonList(client));
        when(clientMapper.clientToClientResponse(client)).thenReturn(new ClientResponse());

        var clients = clientService.getAllClients();

        assertNotNull(clients);
        assertEquals(1, clients.size());
        verify(clientRepository, times(1)).findAll();
    }

    @Test
    void getClientById_Success() {
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        Client result = clientService.getClientById(1L);

        assertNotNull(result);
        assertEquals(client.getId(), result.getId());
    }

    @Test
    void getClientById_NotFound() {
        when(clientRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            clientService.getClientById(1L);
        });

        assertEquals(Messages.CLIENT_NOT_FOUND + 1L, exception.getMessage());
    }

    @Test
    void saveClient_Success() {
        when(clientRepository.findByDescription(saveClientRequest.getDescription())).thenReturn(Optional.empty());
        when(clientMapper.saveClientRequestToClient(saveClientRequest)).thenReturn(client);
        when(clientRepository.save(any())).thenReturn(client);

        Client result = clientService.saveClient(saveClientRequest);

        assertNotNull(result);
        verify(clientRepository, times(1)).save(any(Client.class));
    }

    @Test
    void saveClient_Duplicate() {
        when(clientRepository.findByDescription(saveClientRequest.getDescription())).thenReturn(Optional.of(client));

        Exception exception = assertThrows(DuplicateClientException.class, () -> {
            clientService.saveClient(saveClientRequest);
        });

        assertEquals(Messages.DUPLICATED_CLIENT + saveClientRequest.getDescription(), exception.getMessage());
    }

    @Test
    void deleteClient_Success() {
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        Long deletedId = clientService.deleteClient(1L);

        assertEquals(client.getId(), deletedId);
        verify(clientRepository, times(1)).delete(any(Client.class));
    }

    @Test
    void deleteClient_NotFound() {
        when(clientRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            clientService.deleteClient(1L);
        });

        assertEquals(Messages.CLIENT_NOT_FOUND + 1L, exception.getMessage());
    }

    @Test
    void updateClient_Success() {
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        when(clientRepository.save(any())).thenReturn(client);

        Client updatedClient = clientService.updateClient(1L, updateClientRequest);

        assertNotNull(updatedClient);
        assertEquals("Updated Client", updatedClient.getDescription());
        verify(clientRepository, times(1)).save(any(Client.class));
    }

    @Test
    void updateClient_NotFound() {
        when(clientRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            clientService.updateClient(1L, updateClientRequest);
        });

        assertEquals(Messages.CLIENT_NOT_FOUND + 1L, exception.getMessage());
    }

    @Test
    void addMarketClient_Success() {
        Market market = new Market();
        market.setId(1L);
        market.setCode("CODE");
        market.setCountry("Test Country");

        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        when(marketRepository.findByCodeAndCountry("CODE", "Test Country")).thenReturn(Optional.of(market));
        when(clientMarketRepository.findById(any())).thenReturn(Optional.empty());
        when(clientMarketRepository.save(any())).thenReturn(new ClientMarket());

        clientService.addMarketClient(1L, addMarketClientRequest);

        verify(clientMarketRepository, times(1)).save(any(ClientMarket.class));
    }

    @Test
    void addMarketClient_ClientNotFound() {
        when(clientRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            clientService.addMarketClient(1L, addMarketClientRequest);
        });

        assertEquals(Messages.CLIENT_NOT_FOUND + 1L, exception.getMessage());
    }

    @Test
    void addMarketClient_MarketNotFound() {
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        when(marketRepository.findByCodeAndCountry("CODE", "Test Country")).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            clientService.addMarketClient(1L, addMarketClientRequest);
        });

        assertEquals(Messages.MARKET_NOT_FOUND + "(CODE, Test Country)", exception.getMessage());
    }

    @Test
    void addMarketClient_DuplicateMarket() {
        Market market = new Market();
        market.setId(1L);
        market.setCode("CODE");
        market.setCountry("Test Country");

        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        when(marketRepository.findByCodeAndCountry("CODE", "Test Country")).thenReturn(Optional.of(market));
        when(clientMarketRepository.findById(any())).thenReturn(Optional.of(new ClientMarket()));

        Exception exception = assertThrows(DuplicateClientMarketException.class, () -> {
            clientService.addMarketClient(1L, addMarketClientRequest);
        });

        assertEquals(Messages.DUPLICATED_CLIENT_MARKET, exception.getMessage());
    }

    @Test
    void getStats_Success() {

        Country country = new Country();
        country.setCountryName("Test Country");

        Market market = new Market();
        market.setId(1L);
        market.setCode("CODE");

        ClientMarket clientMarket = new ClientMarket();
        clientMarket.setMarketId(market.getId());


        when(countryRepository.findAll()).thenReturn(Collections.singletonList(country));
        when(marketRepository.findByCountry(country.getCountryName())).thenReturn(Collections.singletonList(market));
        when(clientMarketRepository.count()).thenReturn(10L);
        when(clientMarketRepository.countByMarketId(market.getId())).thenReturn(5L);

        StatsResponse statsResponse = clientService.getStats();


        assertNotNull(statsResponse);
        assertEquals(1, statsResponse.getCountryStats().size());

        CountryStats countryStats = statsResponse.getCountryStats().get(0);
        assertEquals("Test Country", countryStats.getCountry());
        assertEquals(1, countryStats.getMarketInfos().size());

        MarketInfo marketInfo = countryStats.getMarketInfos().get(0);
        assertEquals("CODE", marketInfo.getMarketCode());
        assertEquals(new BigDecimal("50.00"), marketInfo.getPercentage());
    }

}
