package com.hadleynet.ExpenseTrackerV2.controller;

import com.hadleynet.ExpenseTrackerV2.model.Expense;
import com.hadleynet.ExpenseTrackerV2.service.ExpenseService;
import com.hadleynet.ExpenseTrackerV2.service.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expense")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService, TokenService tokenService){
        this.expenseService = expenseService;
    }

    @PostMapping
    public ResponseEntity addExpense(Authentication authentication, @RequestBody Expense expense) {
        expenseService.addExpense(expense, authentication.getName());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteExpense(Authentication authentication, @PathVariable long id){
        expenseService.deleteExpense(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpenses(Authentication authentication) {
        List<Expense> expenses = expenseService.getAllExpenses();
        return ResponseEntity.ok(expenses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(Authentication authentication, @PathVariable long id){
        Expense expense = expenseService.getExpenseById(id);
        return ResponseEntity.ok(expense);
    }

    @PutMapping
    public ResponseEntity<Expense> updateExpense(Authentication authentication, @RequestBody Expense expense){
        Expense result = expenseService.updateExpense(expense, authentication.getName());
        return ResponseEntity.ok(result);
    }

}
