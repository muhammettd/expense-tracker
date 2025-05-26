package com.project.expensetracker_api.dto;

public record CustomerDto(
        Long id,
        String username,
        String password
) {
}
