package com.hadleynet.repository;

import com.hadleynet.ExpenseTrackerV2.ExpenseTrackerV2Application;
import com.hadleynet.ExpenseTrackerV2.model.AppUser;
import com.hadleynet.ExpenseTrackerV2.model.AppUserRole;
import com.hadleynet.ExpenseTrackerV2.model.Expense;
import com.hadleynet.ExpenseTrackerV2.repository.ExpenseRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = ExpenseTrackerV2Application.class)
public class ExpenseRepositoryIntegrationTest {
    Expense expense1;
    @Autowired
    private ExpenseRepository expenseRepository;

    @BeforeEach
    void setUp(){
        AppUser appUser = new AppUser("T", "Scott", "tscott@gmail.com", "password", AppUserRole.USER);
        expense1 = new Expense("Paycheck", "Biweekly pay", new BigDecimal("100.00"), appUser);
        Expense expense2 = new Expense("Gas", "Filled the truck", BigDecimal.valueOf(-100.00), appUser);

        List<AppUser> users = Arrays.asList(appUser);
        List<Expense> expenses = Arrays.asList(expense1, expense2);

//        mongoTemplate.insertAll(users);
//        mongoTemplate.insertAll(expenses);
    }

    @AfterEach
    void tearDown() {
//        mongoTemplate.dropCollection(User.class);
//        mongoTemplate.dropCollection(Expense.class);
    }

    @Test
    void testFindByName() {
        List<Expense> expected = new ArrayList<>();
        expected.add(expense1);
        List<Expense> result = expenseRepository.findByName(expense1.getName());
        assertEquals(result, expected);
    }

}
