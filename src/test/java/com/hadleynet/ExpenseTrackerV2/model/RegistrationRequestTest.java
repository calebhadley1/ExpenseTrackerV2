package com.hadleynet.ExpenseTrackerV2.model;

import com.hadleynet.ExpenseTrackerV2.ExpenseTrackerV2Application;
import com.hadleynet.ExpenseTrackerV2.model.RegistrationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = ExpenseTrackerV2Application.class)
public class RegistrationRequestTest {
    @Test
    void contextLoads() {
    }

    @Test
    void smokeTest(){
        RegistrationRequest registrationRequest = new RegistrationRequest("fName", "lName", "email@email.com", "password");
        assertNotNull(registrationRequest);
    }
}

