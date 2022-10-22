package com.hadleynet.ExpenseTrackerV2.service;

import com.hadleynet.ExpenseTrackerV2.ExpenseTrackerV2Application;
import com.hadleynet.ExpenseTrackerV2.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ExpenseTrackerV2Application.class)
public class ExpenseServiceIntegrationTest {

    @Autowired
    private ExpenseRepository expenseRepository;



}
