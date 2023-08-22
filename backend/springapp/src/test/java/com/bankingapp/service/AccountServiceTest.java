package com.bankingapp.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
		System.out.println("test no. " + cnt + "started");
	}

	@AfterEach
	void tearDown() throws Exception {
		System.out.println("test no. " + cnt + "ended");
		cnt++;
	}

	@Test
	void basicTest1() {
		assertEquals(5,5);
	}

	@Test
	void basicTest2() {
		assertEquals(6,6);
	}
	
	@Test
	void accountAddOK() throws Exception{
		Account newAccount = new Account(10004, 14, (float) 40000.0, "newUser14", "pass@14", 0, 1);
		
		when(accountRepository.save(any(Account.class))).thenReturn(newAccount);
		
		mockMvc.perform(post("/account/add").contentType(MediaType.APPLICATION_JSON)
		        .content(objectMapper.writeValueAsString(newAccount)))
		        .andExpect(status().isCreated());
		
		assertEquals(0,0);
	}
}
