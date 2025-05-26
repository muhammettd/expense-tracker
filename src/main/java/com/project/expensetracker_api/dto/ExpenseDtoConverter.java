package com.project.expensetracker_api.dto;

import com.project.expensetracker_api.model.Expense;
import org.springframework.stereotype.Component;

@Component
public class ExpenseDtoConverter {

    public ExpenseDto convert(Expense expense) {
        ExpenseDto expenseDto = new ExpenseDto(expense.getId(), expense.getEducation(), expense.getShopping(),
                expense.getClothes(), expense.getCreatedDate());
        return expenseDto;
    }


}
