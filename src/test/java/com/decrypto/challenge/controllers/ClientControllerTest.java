package com.decrypto.challenge.controllers;

import com.decrypto.challenge.controllers.dtos.request.AddMarketClientRequest;
import com.decrypto.challenge.controllers.dtos.request.SaveClientRequest;
import com.decrypto.challenge.controllers.dtos.request.UpdateClientRequest;
import com.decrypto.challenge.controllers.dtos.response.StatsResponse;
import com.decrypto.challenge.entities.Client;
import com.decrypto.challenge.services.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ClientControllerTest {

    @InjectMocks
    private ClientController clientController;

    @Mock
    private ClientService clientService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();
    }

    @Test
    void testGetAllClients() throws Exception {
        when(clientService.getAllClients()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/client"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(clientService).getAllClients();
    }

    @Test
    void testGetClientById() throws Exception {
        Long clientId = 1L;
        Client client = new Client();
        when(clientService.getClientById(clientId)).thenReturn(client);

        mockMvc.perform(get("/client/{clientId}", clientId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(clientService).getClientById(clientId);
    }

    @Test
    void testPostClient() throws Exception {
        SaveClientRequest request = new SaveClientRequest( "test description");
        Client client = new Client(1L , "test description",null ,null,null,null);
        when(clientService.saveClient(any(SaveClientRequest.class),anyString())).thenReturn(client);

        mockMvc.perform(post("/client")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer token" )
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(clientService).saveClient(any(SaveClientRequest.class),anyString());
    }

    @Test
    void testDeleteClient() throws Exception {
        Long clientId = 1L;
        when(clientService.deleteClient(clientId)).thenReturn(clientId);

        mockMvc.perform(delete("/client/{clientId}", clientId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(clientService).deleteClient(clientId);
    }

    @Test
    void testUpdateClient() throws Exception {
        Long clientId = 1L;
        UpdateClientRequest request = new UpdateClientRequest("Updated Description");
        Client client = new Client();
        when(clientService.updateClient(anyLong(), any(UpdateClientRequest.class ) ,anyString())).thenReturn(client);

        mockMvc.perform(patch("/client/{clientId}", clientId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer token" )
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(clientService).updateClient(anyLong(), any(UpdateClientRequest.class),anyString());
    }

    @Test
    void testAddMarketToClient() throws Exception {
        Long clientId = 1L;
        AddMarketClientRequest request = new AddMarketClientRequest("CODE", "test country");
        doNothing().when(clientService).addMarketClient(anyLong(), any(AddMarketClientRequest.class));

        mockMvc.perform(post("/client/{clientId}", clientId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk());

        verify(clientService).addMarketClient(anyLong(), any(AddMarketClientRequest.class));
    }

    @Test
    void testGetStats() throws Exception {
        StatsResponse statsResponse = new StatsResponse();
        when(clientService.getStats()).thenReturn(statsResponse);

        mockMvc.perform(get("/client/stats"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(clientService).getStats();
    }
}
