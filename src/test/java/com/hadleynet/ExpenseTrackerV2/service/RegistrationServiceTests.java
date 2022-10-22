package com.hadleynet.ExpenseTrackerV2.service;

import com.hadleynet.ExpenseTrackerV2.model.AppUser;
import com.hadleynet.ExpenseTrackerV2.model.AppUserRole;
import com.hadleynet.ExpenseTrackerV2.model.RegistrationRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class RegistrationServiceTests {

    @Mock
    private AppUserService appUserService;

    @InjectMocks
    private RegistrationService registrationService;

    private RegistrationRequest request;
    private AppUser appUser;

    @BeforeEach
    public void setup() {
        request = new RegistrationRequest(
          "fname", "lname", "test@gmail.com", "pass"
        );
        appUser = new AppUser(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword(),
                AppUserRole.USER
        );
    }

    /*
    Positive Test Cases
     */

    @Test
    public void testRegister() {
        //appUserService.signUpUser() automatically mocked since it is void
        registrationService.register(request);
        verify(appUserService, times(1)).signUpUser(appUser);
        verify(appUserService, times(1)).enableAppUser(request.getEmail());
    }

    /*
    Negative Test Cases
     */


}
