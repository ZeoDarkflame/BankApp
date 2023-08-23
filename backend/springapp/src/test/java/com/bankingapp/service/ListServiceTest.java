package com.bankingapp.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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

import com.bankingapp.models.Customer;
import com.bankingapp.repository.CustomerRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class ListServiceTest {

	static int cnt = 1;
	
	@MockBean
	CustomerRepository customerRepository;
	
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
    public void find_allCustomer_OK() throws Exception {

        List<Customer> customers = Arrays.asList(
                new Customer(102, "User102", "user102@mail.com", "pass@102", 102102),
                new Customer(103, "User103", "user103@mail.com", "pass@103", 103103));

        when(customerRepository.findAll()).thenReturn(customers);

        mockMvc.perform(get("/admin/admin"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].customer_id", is(102)))
                .andExpect(jsonPath("$[0].customer_name", is("User102")))
                .andExpect(jsonPath("$[0].password", is("pass@102")))
                .andExpect(jsonPath("$[0].email", is("user102@mail.com")))
                .andExpect(jsonPath("$[0].contact", is(102102)))
                .andExpect(jsonPath("$[1].customer_id", is(103)))
                .andExpect(jsonPath("$[1].customer_name", is("User103")))
                .andExpect(jsonPath("$[1].password", is("pass@103")))
                .andExpect(jsonPath("$[1].email", is("user103@mail.com")))
                .andExpect(jsonPath("$[1].contact", is(103103)));

        verify(customerRepository, times(1)).findAll();
    }
	
	@Test
	void addCustomerOK() throws JsonProcessingException, Exception {
		Customer newCustomer = new Customer(104, "User104", "user104@mail.com","pass@104",  9968104);
		
		when(customerRepository.save(any(Customer.class))).thenReturn(newCustomer);
		
		mockMvc.perform(post("/admin/customer").contentType(MediaType.APPLICATION_JSON)
		        .content(objectMapper.writeValueAsString(newCustomer)))
		        .andExpect(status().isOk())
		        .andExpect(jsonPath("$.customer_id", is(104)))
                .andExpect(jsonPath("$.customer_name", is("User104")))
                .andExpect(jsonPath("$.password", is("pass@104")))
                .andExpect(jsonPath("$.email", is("user104@mail.com")))
                .andExpect(jsonPath("$.contact", is(9968104)));
		
		  verify(customerRepository, times(1)).save(any(Customer.class));
	}
	
	@Test
    public void update_customer_OK() throws Exception {

		Customer newCustomer = new Customer(101, "User101", "user101@mail.com","pass@101",  9968101);
		when(customerRepository.findById(101)).thenReturn(Optional.of(newCustomer));
		
		Customer updateCustomer = new Customer(101, "User101", "user101@mail.com", "pass@101", 7838101);
        when(customerRepository.save(any(Customer.class))).thenReturn(updateCustomer);

        mockMvc.perform(put("/admin/customer")
                .content(objectMapper.writeValueAsString(updateCustomer))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customer_id", is(101)))
                .andExpect(jsonPath("$.customer_name", is("User101")))
                .andExpect(jsonPath("$.password", is("pass@101")))
                .andExpect(jsonPath("$.email", is("user101@mail.com")))
                .andExpect(jsonPath("$.contact", is(7838101)));

    }
	
	@Test
    public void find_customerIdNotFound_404() throws Exception {
		
		Customer updateCustomer = new Customer(101, "User101", "user101@mail.com", "pass@101", 7838101);
        when(customerRepository.save(any(Customer.class))).thenReturn(updateCustomer);

        mockMvc.perform(put("/customer/customers/101")
                .content(objectMapper.writeValueAsString(updateCustomer))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}
