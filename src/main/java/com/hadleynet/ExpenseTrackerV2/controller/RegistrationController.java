package com.hadleynet.ExpenseTrackerV2.controller;

import com.hadleynet.ExpenseTrackerV2.model.RegistrationRequest;
import com.hadleynet.ExpenseTrackerV2.service.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/register")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    public ResponseEntity register(@RequestBody RegistrationRequest request) {
        registrationService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}