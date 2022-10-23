package com.hadleynet.ExpenseTrackerV2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hadleynet.ExpenseTrackerV2.model.AppUser;
import com.hadleynet.ExpenseTrackerV2.model.RegistrationRequest;
import com.hadleynet.ExpenseTrackerV2.repository.AppUserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TokenControllerWebLayerTests {

    @Autowired
    MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AppUserRepository appUserRepository;

    @BeforeEach
    public void setup() throws Exception {
        //Setup user account
        RegistrationRequest registrationRequest = new RegistrationRequest(
                "fname", "lname", "test@gmail.com", "password"
        );

        MvcResult result = this.mvc.perform(post("/api/register")
                        .content(objectMapper.writeValueAsString(registrationRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @AfterEach
    public void teardown() throws Exception {
        //Delete user
        Optional<AppUser> res = appUserRepository.findByEmail("test@gmail.com");
        appUserRepository.delete(res.get());
    }

    @Test
    void rootWhenAuthenticatedThenSaysHelloUser() throws Exception {
        MvcResult result = this.mvc.perform(post("/api/token")
                        .with(httpBasic("test@gmail.com", "password")))
                .andExpect(status().isOk())
                .andReturn();

        String token = result.getResponse().getContentAsString();

        this.mvc.perform(get("/")
                        .header("Authorization", "Bearer " + token))
                .andExpect(content().string("Hello, test@gmail.com!"));
    }

    @Test
    void rootWhenUnauthenticatedThen401() throws Exception {
        this.mvc.perform(get("/"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void tokenWhenBadCredentialsThen401() throws Exception {
        this.mvc.perform(post("/api/token"))
                .andExpect(status().isUnauthorized());
    }

}
