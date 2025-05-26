package com.project.expensetracker_api.service;

import com.project.expensetracker_api.dto.CreateExpenseRequest;
import com.project.expensetracker_api.dto.ExpenseDtoConverter;
import com.project.expensetracker_api.dto.ExpenseDto;
import com.project.expensetracker_api.model.Customer;
import com.project.expensetracker_api.model.Expense;
import com.project.expensetracker_api.repository.CustomerRepository;
import com.project.expensetracker_api.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ExpenseDtoConverter expenseConverter;
    private final CustomerRepository customerRepository;


    public ExpenseService(ExpenseRepository expenseRepository, ExpenseDtoConverter expenseDto, CustomerRepository customerRepository) {
        this.expenseRepository = expenseRepository;
        this.expenseConverter = expenseDto;
        this.customerRepository = customerRepository;
    }

    public List<ExpenseDto> getAllExpenseByCustomerId(Long customerId) {
        List<Expense> expenses = expenseRepository.findAllByCustomerId(customerId);
        return expenses.stream().map(expenseConverter::convert).collect(Collectors.toList());

    }

    public ExpenseDto createExpenseByUserId(Long userId, CreateExpenseRequest expenseRequest) {
        Optional<Customer> customer = customerRepository.findById(userId);
        Expense expense = new Expense(expenseRequest.education(), expenseRequest.shopping(),
                expenseRequest.clothes(), LocalDateTime.now(), customer.get());
        expenseRepository.save(expense);
        return expenseConverter.convert(expense);
    }

    public List<ExpenseDto> getExpenseLastSevenDays(Long customerId) {
        LocalDateTime sevenDaysAgo = LocalDateTime.now().minusDays(7);
        List<Expense> expenses = expenseRepository.findLast7Days(sevenDaysAgo, customerId);

        return expenses.stream().map(expenseConverter::convert).collect(Collectors.toList());


    }

    public List<ExpenseDto> getExpenseLastThirtyDays(Long customerId) {
        LocalDateTime thirtyDaysAgo = LocalDateTime.now().minusDays(30);
        List<Expense> expenses = expenseRepository.findLast30Days(thirtyDaysAgo, customerId);

        return expenses.stream().map(expenseConverter::convert).collect(Collectors.toList());
    }

    public void deleteExpenseById(Long expenseId) {
        expenseRepository.deleteById(expenseId);
    }

}
