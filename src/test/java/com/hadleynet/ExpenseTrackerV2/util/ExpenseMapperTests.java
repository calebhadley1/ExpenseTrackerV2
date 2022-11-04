package com.hadleynet.ExpenseTrackerV2.util;

import com.hadleynet.ExpenseTrackerV2.ExpenseTrackerV2Application;
import com.hadleynet.ExpenseTrackerV2.model.AppUser;
import com.hadleynet.ExpenseTrackerV2.model.AppUserRole;
import com.hadleynet.ExpenseTrackerV2.model.Expense;
import com.hadleynet.ExpenseTrackerV2.model.ExpenseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = ExpenseTrackerV2Application.class)
public class ExpenseMapperTests {

    @Autowired
    ExpenseMapper expenseMapper;

    @Test
    void testMapToDto(){
        AppUser appUser = new AppUser("T", "Scott", "tscott@gmail.com", "pass", AppUserRole.USER);
        appUser.setId(1L);
        Expense expense = new Expense("name", "desc", BigDecimal.ONE, appUser);
        ExpenseDto expenseDto = expenseMapper.toDto(expense);
        assertEquals(expense.getId(), expenseDto.getId());
        assertEquals(expense.getName(), expenseDto.getName());
        assertEquals(expense.getDescription(), expenseDto.getDescription());
        assertEquals(expense.getAmount(), expenseDto.getAmount());
    }
}

