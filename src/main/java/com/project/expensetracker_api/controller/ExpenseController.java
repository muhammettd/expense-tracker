package com.project.expensetracker_api.controller;

import com.project.expensetracker_api.dto.CreateExpenseRequest;
import com.project.expensetracker_api.dto.ExpenseDto;
import com.project.expensetracker_api.service.ExpenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<List<ExpenseDto>> getAllExpenseByCustomerId(@PathVariable Long customerId) {
        return ResponseEntity.ok(expenseService.getAllExpenseByCustomerId(customerId));
    }

    @PostMapping("/{customerId}")
    public ResponseEntity<ExpenseDto> createExpenseByCustomerId(@PathVariable Long customerId, @RequestBody CreateExpenseRequest expenseRequest) {
        return ResponseEntity.ok(expenseService.createExpenseByUserId(customerId, expenseRequest));
    }

    @GetMapping("/last7days/{customerId}")
    public ResponseEntity<List<ExpenseDto>> getExpenseLastSevenDays(@PathVariable Long customerId) {
        return ResponseEntity.ok(expenseService.getExpenseLastSevenDays(customerId));
    }

    @GetMapping("/last30days/{customerId}")
    public ResponseEntity<List<ExpenseDto>> getExpenseLastThirtyDays(@PathVariable Long customerId) {
        return ResponseEntity.ok(expenseService.getExpenseLastThirtyDays(customerId));
    }

    @DeleteMapping("/{expenseId}")
    public ResponseEntity<Void> deleteExpenseById(@PathVariable Long expenseId) {
        expenseService.deleteExpenseById(expenseId);
        return ResponseEntity.ok().build();
    }


}
