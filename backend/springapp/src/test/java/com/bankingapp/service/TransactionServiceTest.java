package com.bankingapp.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.bankingapp.models.Account;
import com.bankingapp.models.Transaction;
import com.bankingapp.repository.AccountRepository;
import com.bankingapp.repository.CustomerRepository;
import com.bankingapp.repository.TransactionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class TransactionServiceTest {
static int cnt = 1;
	
	@MockBean
	TransactionRepository transactionRepository;
	
	@MockBean
	AccountRepository accountRepository;
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("All tests started");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.println("All tests ended");
	}

	@BeforeEach
	void setUp() throws Exception {
		System.out.println("test no. " + cnt + " started");
	}

	@AfterEach
	void tearDown() throws Exception {
		System.out.println("test no. " + cnt + " ended");
		cnt++;
	}
	
	@Test
	void GetAllTransactions_OK() throws Exception {
		List<Transaction> transactions = Arrays.asList(
				new Transaction(2221, 10001, 10002, null, 1000, 1),
				new Transaction(2222, 10001, 10003, null, 2000, 1),
				new Transaction(2223, 10002, 10003, null, 3000, 1));
		
		when(transactionRepository.findAll()).thenReturn(transactions);
		
		mockMvc.perform(get("/customer/transaction"))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].transaction_id", is(2221)))
        .andExpect(jsonPath("$[0].from_account", is(10001)))
        .andExpect(jsonPath("$[0].to_account", is(10002)))
//        .andExpect(jsonPath("$[0].transaction_time", is(null)))
        .andExpect(jsonPath("$[0].amount", is(1000)))
        .andExpect(jsonPath("$[0].ttype", is(1)))
        .andExpect(jsonPath("$[1].transaction_id", is(2222)))
        .andExpect(jsonPath("$[1].from_account", is(10001)))
        .andExpect(jsonPath("$[1].to_account", is(10003)))
//        .andExpect(jsonPath("$[1].transaction_time", is(null)))
        .andExpect(jsonPath("$[1].amount", is(2000)))
        .andExpect(jsonPath("$[1].ttype", is(1)))
        .andExpect(jsonPath("$[2].transaction_id", is(2223)))
        .andExpect(jsonPath("$[2].from_account", is(10002)))
        .andExpect(jsonPath("$[2].to_account", is(10003)))
//        .andExpect(jsonPath("$[2].transaction_time", is(null)))
        .andExpect(jsonPath("$[2].amount", is(3000)))
        .andExpect(jsonPath("$[2].ttype", is(1)));
		
		verify(transactionRepository, times(1)).findAll();
	}
	
	@Test
	void createTransaction_OK() throws Exception{
		Account mockAccountFrom = new Account(10002, 12, (float) 10000.0, "newUser12", "pass@12", 0, 1);
		when(accountRepository.findById(10002)).thenReturn(Optional.of(mockAccountFrom));
		
		Account mockAccountTo = new Account(10001, 11, (float) 10000.0, "newUser11", "pass@11", 0, 1);
		when(accountRepository.findById(10001)).thenReturn(Optional.of(mockAccountTo));
		
		Transaction newTransaction = new Transaction(2224, 10002, 10001, null, 1500, 1);
		when(transactionRepository.save(any(Transaction.class))).thenReturn(newTransaction);
		
		mockMvc.perform(post("/customer/transaction").contentType(MediaType.APPLICATION_JSON)
		        .content(objectMapper.writeValueAsString(newTransaction)))
		        .andExpect(status().isOk())
				.andExpect(jsonPath("$.amount", is(1500)))
				.andExpect(jsonPath("$.transaction_id", is(2224)))
				.andExpect(jsonPath("$.to_account", is(10001)))
				.andExpect(jsonPath("$.from_account", is(10002)))
				.andExpect(jsonPath("$.ttype", is(1)));
		
		verify(transactionRepository, times(1)).save(any(Transaction.class));
	}
	
	@Test
	void getTransactionByAccId_OK() throws Exception {
		
		List<Transaction> debits = Arrays.asList(
				new Transaction(2221, 10001, 10002, null, 1000, 1),
				new Transaction(2222, 10001, 10003, null, 2000, 1));
		
		List<Transaction> credits = Arrays.asList(
				new Transaction(2223, 10004, 10001, null, 1000, 1),
				new Transaction(2224, 10005, 10001, null, 2000, 1));
		
		when(transactionRepository.customfindAllByFromAccount(10001)).thenReturn(debits);
		when(transactionRepository.customfindAllByToAccount(10002)).thenReturn(credits);
		
		mockMvc.perform(get("/customer/transaction/10001"))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
		
		verify(transactionRepository, times(1)).customfindAllByFromAccount(10001);
//		verify(transactionRepository, times(1)).customfindAllByToAccount(10001);
	}
	
	@Test
	void getWithdrawl_Ok() throws Exception{
		
	}
}
