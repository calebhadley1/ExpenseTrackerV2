package com.hadleynet.ExpenseTrackerV2.service;

import com.hadleynet.ExpenseTrackerV2.model.AppUser;
import com.hadleynet.ExpenseTrackerV2.model.AppUserRole;
import com.hadleynet.ExpenseTrackerV2.model.RegistrationRequest;
import com.hadleynet.ExpenseTrackerV2.repository.AppUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @InjectMocks
    private AppUserService appUserService;

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

        appUserService = new AppUserService(appUserRepository, bCryptPasswordEncoder);
    }

    /*
    Positive Test Cases
     */

    @Test
    public void smokeTest() {
        assertNotNull(appUserService);
    }

    @Test
    public void testLoadUserByUsername() {
        given(appUserRepository.findByEmail(appUser.getUsername())).willReturn(Optional.of(appUser));
        UserDetails result = appUserService.loadUserByUsername(appUser.getUsername());
        verify(appUserRepository, times(1)).findByEmail(appUser.getUsername());
        assertNotNull(result);
    }

    @Test
    public void testSignUpUser() {
        given(appUserRepository.findByEmail(appUser.getUsername())).willReturn(Optional.empty());
        given(bCryptPasswordEncoder.encode(appUser.getPassword())).willReturn("encodedPassword");
        String originalPassword = appUser.getPassword();
        appUserService.signUpUser(appUser);

        verify(appUserRepository, times(1)).findByEmail(appUser.getUsername());
        verify(bCryptPasswordEncoder, times(1)).encode(originalPassword);
        verify(appUserRepository, times(1)).save(appUser);
    }

    @Test
    public void testEnableAppUser() {
        given(appUserRepository.enableAppUser(appUser.getUsername())).willReturn(1);
        int result = appUserService.enableAppUser(appUser.getUsername());
        verify(appUserRepository, times(1)).enableAppUser(appUser.getUsername());
        assertEquals(result, 1);
    }

    /*
    Negative Test Cases
     */

}
