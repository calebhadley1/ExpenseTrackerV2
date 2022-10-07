package com.hadleynet.ExpenseTrackerV2.controller;

import com.hadleynet.ExpenseTrackerV2.service.TokenService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TokenController {

    private final TokenService tokenService;

    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }


    @PostMapping("/token")
    public String token(Authentication authentication) {
        return this.tokenService.token(authentication);
    }

}