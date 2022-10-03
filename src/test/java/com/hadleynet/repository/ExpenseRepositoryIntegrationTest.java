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
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = ExpenseTrackerV2Application.class)
public class ExpenseRepositoryIntegrationTest {
    Expense expense1;
    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    @BeforeEach
    void setUp(){
        User user1 = new User("T", "Scott");

        expense1 = new Expense("Paycheck", "Biweekly pay", new BigDecimal("100.00"), user1);
        Expense expense2 = new Expense("Gas", "Filled the truck", BigDecimal.valueOf(-100.00), user1);

        List<User> users = Arrays.asList(user1);
        List<Expense> expenses = Arrays.asList(expense1, expense2);

        mongoTemplate.insertAll(users);
        mongoTemplate.insertAll(expenses);
    }

    @AfterEach
    void tearDown() {
        mongoTemplate.dropCollection(User.class);
        mongoTemplate.dropCollection(Expense.class);
    }

    @Test
    void testFindByName() {
        List<Expense> expected = new ArrayList<>();
        expected.add(expense1);
        List<Expense> result = expenseRepository.findByName(expense1.getName());
        assertEquals(result, expected);
    }

}
