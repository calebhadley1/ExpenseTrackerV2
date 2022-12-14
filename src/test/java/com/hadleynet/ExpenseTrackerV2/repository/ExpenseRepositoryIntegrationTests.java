package com.hadleynet.ExpenseTrackerV2.repository;

import com.hadleynet.ExpenseTrackerV2.model.AppUser;
import com.hadleynet.ExpenseTrackerV2.model.AppUserRole;
import com.hadleynet.ExpenseTrackerV2.model.Expense;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class ExpenseRepositoryIntegrationTests {
    AppUser appUser1;
    AppUser appUser2;
    Expense expense1;
    Expense expense2;
    Expense expense3;
    List<Expense> expenses;
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp(){
        appUser1 = new AppUser("T", "Scott", "tscott@gmail.com", "password", AppUserRole.USER);
        appUser2 = new AppUser("fname", "lname", "test@gmail.com", "password", AppUserRole.USER);
        expense1 = new Expense("Paycheck", "Biweekly pay", new BigDecimal("100.00"), appUser1);
        expense2 = new Expense("Gas", "Filled the truck", BigDecimal.valueOf(-100.00), appUser1);
        expense3 = new Expense("Something", "A desc", BigDecimal.valueOf(-100.00), appUser2);
        expenses = Arrays.asList(expense1, expense2, expense3);
    }

    /*
    Positive Test Cases
     */

    @Test
    public void testFindAllExpenses() {
        entityManager.persist(appUser1);
        entityManager.persist(appUser2);
        entityManager.persist(expense1);
        entityManager.persist(expense2);
        entityManager.persist(expense3);

        List<Expense> result = expenseRepository.findAll();
        assertThat(result).hasSize(3).containsAll(expenses);
    }

    @Test
    public void testFindAllExpensesByUser() {
        entityManager.persist(appUser1);
        entityManager.persist(appUser2);
        entityManager.persist(expense1);
        entityManager.persist(expense2);
        entityManager.persist(expense3);

        AppUser a = appUserRepository.findByEmail(appUser1.getUsername()).get();

        List<Expense> result = expenseRepository.findByAppUserId(a.getId());
        assertThat(result).hasSize(2).contains(expense1, expense2);
    }

    @Test
    void testFindByName() {
        List<Expense> expected = new ArrayList<>();
        expected.add(expense1);

        entityManager.persist(appUser1);
        entityManager.persist(expense1);
        List<Expense> result = expenseRepository.findByName(expense1.getName()).get();
        assertEquals(result, expected);
    }

    @Test
    void testFindById() {
        Expense expected = expense1;
        entityManager.persist(appUser1);
        entityManager.persist(expense1);

        Expense result = expenseRepository.findById(expense1.getId()).get();
        assertEquals(expected, result);
    }

    @Test
    void testUpdateById() {
        entityManager.persist(appUser1);
        entityManager.persist(expense1);
        Expense expense = new Expense("New name", "New Desc", BigDecimal.valueOf(-100.00), expense1.getAppUser());

        Expense savedExpense = expenseRepository.findById(expense1.getId()).get();
        savedExpense.setName(expense.getName());
        savedExpense.setAmount(expense.getAmount());
        savedExpense.setDescription(expense.getDescription());
        savedExpense.setAppUser(expense.getAppUser());
        expenseRepository.save(savedExpense);

        Expense result = expenseRepository.findById(expense1.getId()).get();

        assertThat(result.getName()).isEqualTo(expense.getName());
        assertThat(result.getAmount()).isEqualTo(expense.getAmount());
        assertThat(result.getDescription()).isEqualTo(expense.getDescription());
        assertThat(result.getAppUser()).isEqualTo(expense.getAppUser());
    }

    @Test
    void testDeleteById() {
        entityManager.persist(appUser1);
        entityManager.persist(expense1);
        entityManager.persist(expense2);

        expenseRepository.deleteById(expense1.getId());
        List<Expense> result = expenseRepository.findAll();
        assertThat(result).hasSize(1).contains(expense2);
    }

    @Test
    void testDeleteAll() {
        entityManager.persist(appUser1);
        entityManager.persist(expense1);
        entityManager.persist(expense2);

        expenseRepository.deleteAll();
        List<Expense> result = expenseRepository.findAll();
        assertThat(result).hasSize(0);
    }


    /*
    Negative Test Cases
     */
    @Test
    public void testFindAllExpenses_EmptyDb() {
        List<Expense> expenses = expenseRepository.findAll();

        assertThat(expenses).isEmpty();
    }
}
