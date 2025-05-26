package com.project.expensetracker_api.dto;

public record ExpenseDto(
        Long id,
        Double education,
        Double shopping,
        Double clothes,
        java.time.LocalDateTime createdDate)
{


}
