package com.hadleynet.ExpenseTrackerV2.service;

import com.hadleynet.ExpenseTrackerV2.model.AppUser;
import com.hadleynet.ExpenseTrackerV2.model.AppUserRole;
import com.hadleynet.ExpenseTrackerV2.model.Expense;
import com.hadleynet.ExpenseTrackerV2.repository.AppUserRepository;
import com.hadleynet.ExpenseTrackerV2.repository.ExpenseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class ExpenseServiceTests {

    @Mock
    private ExpenseRepository expenseRepository;

    @Mock
    private AppUserRepository appUserRepository;

    @InjectMocks
    private ExpenseService expenseService;
    private AppUser appUser1;
    private AppUser appUser2;
    private Expense expense1;
    private Expense expense2;
    private Expense expense3;
    @BeforeEach
    public void setup() {
        appUser1 = new AppUser(
                "fname",
                "lname",
                "test@gmail.com",
                "pass",
                AppUserRole.USER
        );

        appUser2 = new AppUser(
                "fname",
                "lname",
                "test@gmail.com",
                "password",
                AppUserRole.USER
        );

        expense1 = new Expense(
                "Expense name",
                "Expense desc",
                new BigDecimal("0.00"),
                appUser1
        );

        expense2 = new Expense(
                "Expense name2",
                "Expense desc2",
                new BigDecimal("1.00"),
                appUser1
        );

        expense3 = new Expense(
                "Something",
                "A desc",
                BigDecimal.valueOf(-100.00),
                appUser2
        );

        expenseService = new ExpenseService(expenseRepository, appUserRepository);
    }

    /*
    Positive Test Cases
     */

    @Test
    public void testAddExpense() {
        given(appUserRepository.findByEmail(appUser1.getUsername())).willReturn(Optional.of(appUser1));
        expenseService.addExpense(expense1, appUser1.getUsername());
        verify(appUserRepository, times(1)).findByEmail(appUser1.getUsername());
        verify(expenseRepository, times(1)).save(expense1);
        // Check expense has associated user
        assertEquals(expense1.getAppUser(), appUser1);
    }

    @Test
    public void testGetAllExpenses() {
        List<Expense> expected = new ArrayList<>();
        expected.add(expense1);
        expected.add(expense2);
        expected.add(expense3);
        given(expenseRepository.findAll()).willReturn(expected);
        List<Expense> result = expenseService.getAllExpenses("", false);
        verify(expenseRepository, times(1)).findAll();
        assertEquals(result, expected);
    }

    @Test
    public void testGetAllExpensesByUser() {
        List<Expense> expected = new ArrayList<>();
        expected.add(expense1);
        expected.add(expense2);
        given(appUserRepository.findByEmail(appUser1.getUsername())).willReturn(Optional.of(appUser1));
        appUser1.setId(0L);
        given(expenseRepository.findByAppUserId(appUser1.getId())).willReturn(expected);
        List<Expense> result = expenseService.getAllExpenses(appUser1.getUsername(), true);
        verify(appUserRepository, times(1)).findByEmail(appUser1.getUsername());
        verify(expenseRepository, times(1)).findByAppUserId(appUser1.getId());
        assertEquals(result, expected);
    }

    @Test
    public void testGetExpenseById() {
        long id = 1;
        given(expenseRepository.findById(id)).willReturn(Optional.of(expense1));
        Expense result = expenseService.getExpenseById(id);
        verify(expenseRepository, times(1)).findById(id);
        assertEquals(expense1, result);
    }
    @Test
    public void testDeleteExpense() {
        long id = 1;
        expenseService.deleteExpense(id);
        verify(expenseRepository, times(1)).deleteById(id);
    }
    @Test
    public void testUpdateExpense() {
        given(appUserRepository.findByEmail(appUser1.getUsername())).willReturn(Optional.of(appUser1));
        given(expenseRepository.findById(0L)).willReturn(Optional.of(expense1));
        Expense result = expenseService.updateExpense(expense1, appUser1.getUsername());
        verify(appUserRepository, times(1)).findByEmail(appUser1.getUsername());
        verify(expenseRepository, times(1)).findById(0L);
        verify(expenseRepository, times(1)).save(expense1);
        assertEquals(result, expense1);
    }

    /*
    Negative Test Cases
     */

}
