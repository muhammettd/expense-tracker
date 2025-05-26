package com.project.expensetracker_api.dto;

public record CreateCustomerRequest(
        String username,
        String password
) {
}
