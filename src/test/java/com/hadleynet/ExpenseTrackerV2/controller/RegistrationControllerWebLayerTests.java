package com.hadleynet.ExpenseTrackerV2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RegistrationControllerWebLayerTests {

    @Autowired
    MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AppUserRepository appUserRepository;

    @BeforeEach
    public void setup() {
        //Delete User
        appUserRepository.deleteAll();
    }
    @AfterEach
    public void teardown() {
        //Delete User
        appUserRepository.deleteAll();
    }

    @Test
    void testRegister() throws Exception {
        RegistrationRequest registrationRequest = new RegistrationRequest(
                "fname", "lname", "test@gmail.com", "password"
        );
        this.mvc.perform(post("/api/register")
                        .content(objectMapper.writeValueAsString(registrationRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
    }
}