package com.hadleynet.model;

import com.hadleynet.ExpenseTrackerV2.ExpenseTrackerV2Application;
import com.hadleynet.ExpenseTrackerV2.model.Expense;
import com.hadleynet.ExpenseTrackerV2.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = ExpenseTrackerV2Application.class)
public class ExpenseTest {
    @Test
    void contextLoads() {
    }

    @Test
    void smokeTest(){
        User user = new User("T", "Scott");
        Expense expense = new Expense("Paycheck", "Biweekly pay", new BigDecimal(100.00), user);
        assertNotNull(expense);
    }
}
