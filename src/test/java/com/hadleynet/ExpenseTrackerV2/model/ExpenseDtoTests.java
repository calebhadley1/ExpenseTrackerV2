package com.hadleynet.ExpenseTrackerV2.model;

import com.hadleynet.ExpenseTrackerV2.ExpenseTrackerV2Application;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = ExpenseTrackerV2Application.class)
public class ExpenseDtoTests {

    @Test
    void testExpenseDto(){
        ExpenseDto expenseDto = new ExpenseDto(1, "", "", BigDecimal.ONE, 1);
    }
}

