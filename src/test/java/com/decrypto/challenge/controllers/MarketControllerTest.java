package com.decrypto.challenge.controllers;

import com.decrypto.challenge.controllers.dtos.request.SaveMarketRequest;
import com.decrypto.challenge.controllers.dtos.request.UpdateMarketRequest;
import com.decrypto.challenge.controllers.dtos.response.MarketResponse;
import com.decrypto.challenge.entities.Market;
import com.decrypto.challenge.services.MarketService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class MarketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private MarketService marketService;

    @InjectMocks
    private MarketController marketController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(marketController).build();
    }

    @Test
    public void testGetAllMarkets() throws Exception {
        List<MarketResponse> markets = Arrays.asList(new MarketResponse(1L, "CODE","test description","test country"), new MarketResponse(2L, "CODE","test description","test country"));
        when(marketService.getAllMarkets()).thenReturn(markets);

        mockMvc.perform(MockMvcRequestBuilders.get("/market"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetMarketById() throws Exception {
        Market market = new Market(1L, "CODE","test description","test country",null,null,null,null);
        when(marketService.getMarketById(anyLong())).thenReturn(market);

        mockMvc.perform(MockMvcRequestBuilders.get("/market/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testPostMarket() throws Exception {
        SaveMarketRequest request = new SaveMarketRequest("CODE","test description","test country");
        Market market = new Market(1L, "CODE","test country","test country",null,null,null,null);
        when(marketService.saveMarket(any(SaveMarketRequest.class),anyString())).thenReturn(market);

        mockMvc.perform(MockMvcRequestBuilders.post("/market")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer token" )
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testDeleteMarket() throws Exception {
        when(marketService.deleteMarket(anyLong())).thenReturn(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/market/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("1"));
    }

    @Test
    public void testUpdateMarket() throws Exception {
        UpdateMarketRequest request = new UpdateMarketRequest("CODE","test description","test country");
        Market market = new Market(1L, "CODE","test description","test country",null,null,null,null);
        when(marketService.updateMarket(anyLong(), any(UpdateMarketRequest.class),anyString())).thenReturn(market);

        mockMvc.perform(MockMvcRequestBuilders.patch("/market/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer token" )
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }
}
