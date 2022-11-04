package com.hadleynet.ExpenseTrackerV2.service;

import com.hadleynet.ExpenseTrackerV2.exception.ExpenseNotFoundException;
import com.hadleynet.ExpenseTrackerV2.model.AppUser;
import com.hadleynet.ExpenseTrackerV2.model.Expense;
import com.hadleynet.ExpenseTrackerV2.repository.AppUserRepository;
import com.hadleynet.ExpenseTrackerV2.repository.ExpenseRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {
    private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";
    private final ExpenseRepository expenseRepository;
    private final AppUserRepository appUserRepository;

    public ExpenseService(ExpenseRepository expenseRepository, AppUserRepository appUserRepository){
        this.expenseRepository = expenseRepository;
        this.appUserRepository = appUserRepository;
    }

    public void addExpense(Expense expense, String email) {
        AppUser appUser = appUserRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, email)));
        expense.setAppUser(appUser);
        expenseRepository.save(expense);
    }

    public List<Expense> getAllExpenses(String email, boolean byUserFlag) {
        if(byUserFlag){
            AppUser appUser = appUserRepository.findByEmail(email).orElseThrow(

            );
            return expenseRepository.findByAppUserId(appUser.getId());
        }
        // Add check so only admins can do this
        return expenseRepository.findAll();
    }

    public Expense getExpenseById(long id) {
        return expenseRepository.findById(id).orElseThrow(() ->
            new ExpenseNotFoundException(String.format("Cannot find Expense by Id %s", id)));
    }

    public void deleteExpense(long id) {
        expenseRepository.deleteById(id);
    }

    public Expense updateExpense(Expense expense, String email) {
        AppUser appUser = appUserRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, email)));

        Expense savedExpense = expenseRepository.findById(expense.getId())
                .orElseThrow(() -> new RuntimeException(
                        String.format("Cannot Find Expense by ID %s", expense.getId())));

        savedExpense.setName(expense.getName());
        savedExpense.setAmount(expense.getAmount());
        savedExpense.setDescription(expense.getDescription());
        savedExpense.setAppUser(appUser);

        expenseRepository.save(savedExpense);
        return savedExpense;
    }
}
