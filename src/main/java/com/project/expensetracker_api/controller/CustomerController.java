package com.project.expensetracker_api.controller;

import com.project.expensetracker_api.dto.CreateCustomerRequest;
import com.project.expensetracker_api.dto.CustomerDto;
import com.project.expensetracker_api.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;


    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/register")
    public CustomerDto register(@RequestBody CreateCustomerRequest customerRequest) {
        return customerService.register(customerRequest);
    }

    @PostMapping("/login")
    public String login(@RequestBody CreateCustomerRequest customerRequest) {
        return customerService.login(customerRequest);
    }


}
