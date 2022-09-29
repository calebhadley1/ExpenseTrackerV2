package com.hadleynet.repository;

import com.hadleynet.ExpenseTrackerV2.ExpenseTrackerV2Application;
import com.hadleynet.ExpenseTrackerV2.model.Expense;
import com.hadleynet.ExpenseTrackerV2.model.User;
import com.hadleynet.ExpenseTrackerV2.repository.ExpenseRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = ExpenseTrackerV2Application.class)
public class ExpenseRepositoryTest {
    User user;
    Expense expense;
    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    @BeforeEach
    void setUp(){
        mongoTemplate.dropCollection(Expense.class);

        user = new User("T", "Scott");
        expense = new Expense("Paycheck", "Biweekly pay", new BigDecimal(100.00), user);
        expenseRepository.save(expense);
    }

    @AfterEach
    void tearDown() {
        mongoTemplate.dropCollection(Expense.class);
    }

    @Test
    void testFindByName() {
        List<Expense> expected = new ArrayList<>();
        expected.add(expense);
        List<Expense> result = expenseRepository.findByName(expense.getName());
        assertEquals(result, expected);
    }

}
