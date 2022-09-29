package com.hadleynet.model;

import com.hadleynet.ExpenseTrackerV2.ExpenseTrackerV2Application;
import com.hadleynet.ExpenseTrackerV2.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = ExpenseTrackerV2Application.class)
public class UserTest {
    @Test
    void contextLoads() {
    }

    @Test
    void smokeTest(){
        User user = new User("T", "Scott");
        assertNotNull(user);
    }
}

