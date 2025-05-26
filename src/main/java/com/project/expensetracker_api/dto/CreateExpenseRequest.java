package com.project.expensetracker_api.dto;

public record CreateExpenseRequest(
        Double education,
        Double shopping,
        Double clothes
) {
}
