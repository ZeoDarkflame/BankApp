package com.bankingapp.controllers;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.bankingapp.models.AuthRequest;
import com.bankingapp.models.AuthResponse;
import com.bankingapp.service.CustomerService;
import com.bankingapp.util.JwtUtil;

@CrossOrigin(origins="*",allowedHeaders="*")
@RestController
public class WelcomeController {

    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private CustomerService customerService;

    @GetMapping("/")
    public String welcome() {
        return "Welcome to java !!";
    }


    
    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
            if(!customerService.CheckActiveCustomer(authRequest.getUserName())) {
//            	System.out.println("blocked");
            	throw new Exception("User Blocked");
            }
            customerService.LoginAttempt(authRequest.getUserName(), true);
        } catch (Exception ex) {
        	System.out.println("Login failed");
        	customerService.LoginAttempt(authRequest.getUserName(), false);
        	if(!customerService.CheckActiveCustomer(authRequest.getUserName())) {
        		throw new Exception("More than 3 incorrect attempts user blocked");
        	}else {
        		throw new Exception("inavalid username/password");
        	}
        }
        return jwtUtil.generateToken(authRequest.getUserName());
    } 
    
    @GetMapping(value = "/validate")
	public boolean getValidation(@RequestHeader("Authorization") String token){
		token = token.substring(7);
		AuthResponse auth = new AuthResponse();
	
		//log.info("Token validation for "+jwtUtil.extractUsername(token));
		
		if(jwtUtil.validateToken(token)) {
			
			System.out.println("Token validated");
			return true;
		}
		else {
			System.out.println("Token NOT validated");
			return false;

	}
    }}


