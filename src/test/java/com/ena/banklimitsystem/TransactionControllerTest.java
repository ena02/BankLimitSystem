package com.ena.banklimitsystem;

import com.ena.banklimitsystem.controller.TransactionController;
import com.ena.banklimitsystem.model.TransactionEntity;
import com.ena.banklimitsystem.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class TransactionControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionController transactionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(transactionController).build();
    }

    @Test
    void testGetAllTransactions() throws Exception {
        when(transactionService.getAllTransactions()).thenReturn(Collections.emptyList());
        mockMvc.perform(MockMvcRequestBuilders.get("/transactions/allTransaction")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());
    }

    @Test
    void testGetTransactionsById() throws Exception {
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setId(1);
        when(transactionService.getTransactionById(anyInt())).thenReturn(transactionEntity);
        mockMvc.perform(MockMvcRequestBuilders.get("/transactions/transactionById/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    // Add more tests for other methods as needed
}
