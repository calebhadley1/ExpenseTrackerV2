package com.hadleynet.ExpenseTrackerV2.service;

import com.hadleynet.ExpenseTrackerV2.exception.ExpenseNotFoundException;
import com.hadleynet.ExpenseTrackerV2.model.Expense;
import com.hadleynet.ExpenseTrackerV2.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository){
        this.expenseRepository = expenseRepository;
    }

    public void addExpense(Expense expense) {
        expenseRepository.save(expense);
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Expense getExpenseById(long id) {
        return expenseRepository.findById(id).orElseThrow(() ->
            new ExpenseNotFoundException(String.format("Cannot find Expense by Id %s", id)));
    }

    public void deleteExpense(long id) {
        expenseRepository.deleteById(id);
    }

    public Expense updateExpense(Expense expense) {
        Expense savedExpense = expenseRepository.findById(expense.getId())
                .orElseThrow(() -> new RuntimeException(
                        String.format("Cannot Find Expense by ID %s", expense.getId())));

        savedExpense.setName(expense.getName());
        savedExpense.setAmount(expense.getAmount());
        savedExpense.setDescription(expense.getDescription());
        savedExpense.setUser(expense.getUser());

        expenseRepository.save(savedExpense);
        return savedExpense;
    }
}
