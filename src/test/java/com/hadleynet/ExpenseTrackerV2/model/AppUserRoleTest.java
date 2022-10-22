package com.hadleynet.ExpenseTrackerV2.model;

import com.hadleynet.ExpenseTrackerV2.ExpenseTrackerV2Application;
import com.hadleynet.ExpenseTrackerV2.model.AppUser;
import com.hadleynet.ExpenseTrackerV2.model.AppUserRole;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = ExpenseTrackerV2Application.class)
public class AppUserRoleTest {
    @Test
    void contextLoads() {
    }

    @Test
    void testAppUserRoleUser(){
        AppUserRole appUserRole = AppUserRole.USER;
        assertNotNull(appUserRole);
    }

    @Test
    void testAppUserRoleAdmin(){
        AppUserRole appUserRole = AppUserRole.ADMIN;
        assertNotNull(appUserRole);
    }
}

