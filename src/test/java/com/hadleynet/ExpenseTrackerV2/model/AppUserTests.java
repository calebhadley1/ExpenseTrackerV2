package com.hadleynet.ExpenseTrackerV2.model;

import com.hadleynet.ExpenseTrackerV2.ExpenseTrackerV2Application;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = ExpenseTrackerV2Application.class)
public class AppUserTests {
    @Test
    void contextLoads() {
    }

    @Test
    void smokeTest(){
        AppUser appUser = new AppUser("T", "Scott", "tscott@gmail.com", "pass", AppUserRole.USER);
        assertNotNull(appUser);
    }
}

