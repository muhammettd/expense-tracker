package com.project.expensetracker_api.dto;

import com.project.expensetracker_api.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerDtoConverter {

    public CustomerDto convert(Customer customer) {
        CustomerDto customerDto = new CustomerDto(customer.getId(), customer.getUsername(), customer.getPassword());
        return customerDto;
    }

}
