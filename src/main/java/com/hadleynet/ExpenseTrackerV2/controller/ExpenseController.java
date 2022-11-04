package com.hadleynet.ExpenseTrackerV2.controller;

import com.hadleynet.ExpenseTrackerV2.model.Expense;
import com.hadleynet.ExpenseTrackerV2.model.ExpenseDto;
import com.hadleynet.ExpenseTrackerV2.service.ExpenseService;
import com.hadleynet.ExpenseTrackerV2.service.TokenService;
import com.hadleynet.ExpenseTrackerV2.util.ExpenseMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/expense")
public class ExpenseController {

    private final ExpenseService expenseService;
    private final ExpenseMapper mapper;

    public ExpenseController(ExpenseService expenseService, ExpenseMapper mapper){
        this.expenseService = expenseService;
        this.mapper = mapper;
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
    public ResponseEntity<List<ExpenseDto>> getAllExpenses(Authentication authentication, @RequestParam(defaultValue = "true") boolean byUser) {
        List<Expense> expenses = expenseService.getAllExpenses(authentication.getName(), byUser);
        System.out.println(expenses);
        List<ExpenseDto> expenseDtos = expenses
                .stream()
                .map(mapper::toDto)
                .collect(toList());
        return ResponseEntity.ok(expenseDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseDto> getExpenseById(Authentication authentication, @PathVariable long id){
        Expense expense = expenseService.getExpenseById(id);
        ExpenseDto expenseDto = mapper.toDto(expense);
        return ResponseEntity.ok(expenseDto);
    }

    @PutMapping
    public ResponseEntity<ExpenseDto> updateExpense(Authentication authentication, @RequestBody Expense expense){
        Expense result = expenseService.updateExpense(expense, authentication.getName());
        ExpenseDto expenseDto = mapper.toDto(result);
        return ResponseEntity.ok(expenseDto);
    }

}
