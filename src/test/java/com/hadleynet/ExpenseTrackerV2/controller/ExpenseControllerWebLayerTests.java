package com.hadleynet.ExpenseTrackerV2.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hadleynet.ExpenseTrackerV2.model.Expense;
import com.hadleynet.ExpenseTrackerV2.model.ExpenseDto;
import com.hadleynet.ExpenseTrackerV2.model.RegistrationRequest;
import com.hadleynet.ExpenseTrackerV2.repository.AppUserRepository;
import com.hadleynet.ExpenseTrackerV2.repository.ExpenseRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ExpenseControllerWebLayerTests {

    @Autowired
    MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    private String token;
    private Expense expense;

    @BeforeEach
    public void setup() throws Exception {
        //Setup user account
        RegistrationRequest registrationRequest = new RegistrationRequest(
                "fname", "lname", "test@gmail.com", "password"
        );

        MvcResult userResult = this.mvc.perform(post("/api/register")
                        .content(objectMapper.writeValueAsString(registrationRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        //Get auth token
        MvcResult tokenResult = this.mvc.perform(post("/api/token")
                        .with(httpBasic("test@gmail.com", "password")))
                .andExpect(status().isOk())
                .andReturn();

        token = tokenResult.getResponse().getContentAsString();
        expense = new Expense("Paycheck", "Biweekly pay", new BigDecimal("100.00"), null);
    }

    @AfterEach
    public void teardown() {
        //Delete user
        expenseRepository.deleteAll();
        appUserRepository.deleteAll();
    }

    @Test
    void testAddExpense() throws Exception {
        this.mvc.perform(post("/api/expense")
                        .header("Authorization", "Bearer " + token)
                        .content(objectMapper.writeValueAsString(expense))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    void testGetAllExpenses() throws Exception {
        //Get all Expenses
        MvcResult result = this.mvc.perform(get("/api/expense")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        List<ExpenseDto> getAllResponse = new ObjectMapper().readValue(json, new TypeReference<List<ExpenseDto>>(){});
        assertNotNull(getAllResponse);
    }

    @Test
    void testGetExpenseById() throws Exception {
        //Insert Expense
        this.mvc.perform(post("/api/expense")
                        .header("Authorization", "Bearer " + token)
                        .content(objectMapper.writeValueAsString(expense))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
        //Get All Expenses
        MvcResult getAllResult = this.mvc.perform(get("/api/expense")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andReturn();

        String getAllJson = getAllResult.getResponse().getContentAsString();
        List<ExpenseDto> getAllResponse = new ObjectMapper().readValue(getAllJson, new TypeReference<List<ExpenseDto>>(){});
        //Get Expense by Id
        MvcResult result = this.mvc.perform(get("/api/expense/" + getAllResponse.get(0).getId())
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andReturn();

        String getByIdJson = result.getResponse().getContentAsString();
        ExpenseDto getByIdResponse = new ObjectMapper().readValue(getByIdJson, new TypeReference<ExpenseDto>(){});
        assertEquals(getAllResponse.get(0), getByIdResponse);
    }

    @Test
    void testDeleteExpense() throws Exception {
        //Insert Expense
        this.mvc.perform(post("/api/expense")
                        .header("Authorization", "Bearer " + token)
                        .content(objectMapper.writeValueAsString(expense))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
        //Get all Expenses for user
        MvcResult result = this.mvc.perform(get("/api/expense")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        List<ExpenseDto> getAllResponse = new ObjectMapper().readValue(json, new TypeReference<List<ExpenseDto>>(){});
        //Delete the first Expense
        this.mvc.perform(delete("/api/expense/" + getAllResponse.get(0).getId())
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isNoContent())
                .andReturn();
    }

    @Test
    void testUpdateExpense() throws Exception {
        //Insert Expense
        this.mvc.perform(post("/api/expense")
                        .header("Authorization", "Bearer " + token)
                        .content(objectMapper.writeValueAsString(expense))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
        //Get all Expenses for user
        MvcResult getResult = this.mvc.perform(get("/api/expense")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andReturn();

        String getJson = getResult.getResponse().getContentAsString();
        List<ExpenseDto> getAllResponse = new ObjectMapper().readValue(getJson, new TypeReference<List<ExpenseDto>>(){});
        //Update first Expense in List
        ExpenseDto firstExpense = getAllResponse.get(0);
        firstExpense.setName("A new name");
        firstExpense.setDescription("A new desc");
        MvcResult updateResult = this.mvc.perform(put("/api/expense")
                        .header("Authorization", "Bearer " + token)
                        .content(objectMapper.writeValueAsString(firstExpense))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String updateJson = updateResult.getResponse().getContentAsString();
        List<ExpenseDto> updateResponse = new ObjectMapper().readValue(getJson, new TypeReference<List<ExpenseDto>>(){});
        assertThat(updateResponse.get(0).getName().equals(firstExpense.getName()));
        assertThat(updateResponse.get(0).getDescription().equals(firstExpense.getDescription()));
    }
}
