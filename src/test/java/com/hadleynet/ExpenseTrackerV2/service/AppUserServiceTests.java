package com.hadleynet.ExpenseTrackerV2.service;

import com.hadleynet.ExpenseTrackerV2.model.AppUser;
import com.hadleynet.ExpenseTrackerV2.model.AppUserRole;
import com.hadleynet.ExpenseTrackerV2.model.RegistrationRequest;
import com.hadleynet.ExpenseTrackerV2.repository.AppUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class AppUserServiceTests {

    @Mock
    private AppUserRepository appUserRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private AppUserService appUserService = new AppUserService(appUserRepository, bCryptPasswordEncoder);

    private AppUser appUser;

    @BeforeEach
    public void setup() {
        appUser = new AppUser(
                "fname",
                "lname",
                "test@gmail.com",
                "pass",
                AppUserRole.USER
        );
    }

    /*
    Positive Test Cases
     */

    @Test
    public void testLoadUserByUsername() {
        given(appUserRepository.findByEmail(appUser.getUsername())).willReturn(Optional.of(appUser));
        UserDetails result = appUserService.loadUserByUsername(appUser.getUsername());
        verify(appUserRepository, times(1)).findByEmail(appUser.getUsername());
        assertNotNull(result);
    }

    /*
    Negative Test Cases
     */

}
