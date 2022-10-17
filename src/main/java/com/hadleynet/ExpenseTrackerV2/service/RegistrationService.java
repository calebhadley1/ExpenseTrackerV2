package com.hadleynet.ExpenseTrackerV2.service;

import com.hadleynet.ExpenseTrackerV2.model.AppUser;
import com.hadleynet.ExpenseTrackerV2.model.AppUserRole;
import com.hadleynet.ExpenseTrackerV2.model.RegistrationRequest;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final AppUserService appUserService;

    public RegistrationService(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    public void register(RegistrationRequest request) {

        appUserService.signUpUser(
                new AppUser(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        AppUserRole.USER
                )
        );

    }
}
