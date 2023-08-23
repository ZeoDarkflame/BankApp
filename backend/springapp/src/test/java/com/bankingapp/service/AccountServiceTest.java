package com.bankingapp.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Ignore;
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

import com.bankingapp.models.Account;
import com.bankingapp.repository.AccountRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class AccountServiceTest {

	static int cnt = 1;
	
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
    public void find_allAccount_OK() throws Exception {

        List<Account> accounts = Arrays.asList(
                new Account(10002, 12, (float) 20000.0, "newUser12", "pass@12", 0, 1),
                new Account(10003, 13, (float) 30000.0, "newUser13", "pass@13", 0, 1));

        when(accountRepository.findAll()).thenReturn(accounts);

        mockMvc.perform(get("/account/readall"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].account_id", is(10002)))
                .andExpect(jsonPath("$[0].customer_id", is(12)))
                .andExpect(jsonPath("$[0].balance", is(20000.0)))
                .andExpect(jsonPath("$[0].username", is("newUser12")))
                .andExpect(jsonPath("$[0].transactionPassword", is("pass@12")))
                .andExpect(jsonPath("$[0].activity", is(0)))
		        .andExpect(jsonPath("$[0].accountType", is(1)))
                .andExpect(jsonPath("$[1].account_id", is(10003)))
                .andExpect(jsonPath("$[1].customer_id", is(13)))
                .andExpect(jsonPath("$[1].balance", is(30000.0)))
                .andExpect(jsonPath("$[1].username", is("newUser13")))
                .andExpect(jsonPath("$[1].transactionPassword", is("pass@13")))
                .andExpect(jsonPath("$[1].activity", is(0)))
		        .andExpect(jsonPath("$[1].accountType", is(1)));

        verify(accountRepository, times(1)).findAll();
    }
	
	@Test
	public void find_accountId_OK() throws Exception{
		
		Account mockAccount = new Account(10001, 11, (float) 10000.0, "newUser11", "pass@11", 0, 1);
		when(accountRepository.findById(10001)).thenReturn(Optional.of(mockAccount));
		
		mockMvc.perform(get("/account/read/10001"))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.account_id", is(10001)))
        .andExpect(jsonPath("$.customer_id", is(11)))
        .andExpect(jsonPath("$.balance", is(10000.0)))
        .andExpect(jsonPath("$.username", is("newUser11")))
        .andExpect(jsonPath("$.transactionPassword", is("pass@11")))
        .andExpect(jsonPath("$.activity", is(0)))
        .andExpect(jsonPath("$.accountType", is(1)));

		verify(accountRepository, times(1)).findById(10001);
	}
	
	@Test
    public void find_accountIdNotFound_404() throws Exception {
        mockMvc.perform(get("/account/read/10000")).andExpect(status().isNotFound());
    }
	
	@Test
	void accountAddOK() throws Exception{
		Account newAccount = new Account(10004, 14, (float) 40000.0, "newUser14", "pass@14", 0, 1);
		System.out.println(newAccount.getAccountType());
		when(accountRepository.save(any(Account.class))).thenReturn(newAccount);
		
		mockMvc.perform(post("/account/add").contentType(MediaType.APPLICATION_JSON)
		        .content(objectMapper.writeValueAsString(newAccount)))
		        .andExpect(status().isOk())
		        .andExpect(jsonPath("$.account_id", is(10004)))
		        .andExpect(jsonPath("$.customer_id", is(14)))
		        .andExpect(jsonPath("$.balance", is(40000.0)))
		        .andExpect(jsonPath("$.username", is("newUser14")))
		        .andExpect(jsonPath("$.transactionPassword", is("pass@14")))
		        .andExpect(jsonPath("$.activity", is(0)))
		        .andExpect(jsonPath("$.accountType", is(1)));
		
		verify(accountRepository, times(1)).save(any(Account.class));
	}
	
	@Test
    public void update_account_OK() throws Exception {
		
		Account mockAccount = new Account(10001, 11, (float) 10000.0, "newUser11", "pass@11", 0, 0);
		when(accountRepository.findById(10001)).thenReturn(Optional.of(mockAccount));

		Account updateAccount = new Account(10001, 11, (float) 11000.0, "newUser11", "password@11", 0, 1);
        when(accountRepository.save(any(Account.class))).thenReturn(updateAccount);

        mockMvc.perform(put("/account/update/10001")
                .content(objectMapper.writeValueAsString(updateAccount))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.account_id", is(10001)))
                .andExpect(jsonPath("$.customer_id", is(11)))
                .andExpect(jsonPath("$.balance", is(11000.0)))
                .andExpect(jsonPath("$.username", is("newUser11")))
                .andExpect(jsonPath("$.transactionPassword", is("password@11")))
                .andExpect(jsonPath("$.activity", is(0)))
                .andExpect(jsonPath("$.accountType", is(1)));

    }
	
	@Test
    public void delete_account_OK() throws Exception {

		Account mockAccount = new Account(10001, 11, (float) 10000.0, "newUser11", "pass@11", 0, 1);
		when(accountRepository.findById(10001)).thenReturn(Optional.of(mockAccount));
		
        doNothing().when(accountRepository).deleteById(10001);

        mockMvc.perform(delete("/account/delete/10001"))
                .andExpect(status().isOk());

        verify(accountRepository, times(1)).deleteById(10001);
    }
}
