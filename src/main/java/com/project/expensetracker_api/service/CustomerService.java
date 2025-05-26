package com.project.expensetracker_api.service;

import com.project.expensetracker_api.dto.CreateCustomerRequest;
import com.project.expensetracker_api.dto.CustomerDto;
import com.project.expensetracker_api.dto.CustomerDtoConverter;
import com.project.expensetracker_api.model.Customer;
import com.project.expensetracker_api.repository.CustomerRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final AuthenticationManager authManager;
    private final CustomerRepository customerRepository;
    private final BCryptPasswordEncoder encoder;
    private final CustomerDtoConverter converter;
    private final JwtService jwtService;

    public CustomerService(AuthenticationManager authManager, CustomerRepository customerRepository, BCryptPasswordEncoder encoder,
                           CustomerDtoConverter converter, JwtService jwtService) {
        this.authManager = authManager;
        this.customerRepository = customerRepository;
        this.encoder = encoder;
        this.converter = converter;
        this.jwtService = jwtService;
    }

    public CustomerDto register(CreateCustomerRequest customerRequest) {
        Customer customer = new Customer(customerRequest.username(), encoder.encode(customerRequest.password()));
        return converter.convert(customerRepository.save(customer));
    }


    public String login(CreateCustomerRequest customerRequest) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(customerRequest.username(), customerRequest.password()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(customerRequest.username());
        } else {
            return "FALSE CUSTOMER";
        }

    }
}
