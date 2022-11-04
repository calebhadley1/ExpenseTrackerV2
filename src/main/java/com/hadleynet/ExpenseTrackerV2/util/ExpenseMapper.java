package com.hadleynet.ExpenseTrackerV2.util;

import com.hadleynet.ExpenseTrackerV2.model.Expense;
import com.hadleynet.ExpenseTrackerV2.model.ExpenseDto;
import org.springframework.stereotype.Component;

@Component
public class ExpenseMapper {
    public ExpenseDto toDto(Expense expense) {
        System.out.println("mapper called");
        System.out.println(expense);
        long appUserId = expense.getAppUser().getId();
        return new ExpenseDto(expense.getId(), expense.getName(), expense.getDescription(), expense.getAmount(), appUserId);
    }
}
