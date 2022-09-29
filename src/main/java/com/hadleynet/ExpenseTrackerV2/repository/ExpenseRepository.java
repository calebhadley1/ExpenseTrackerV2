package com.hadleynet.ExpenseTrackerV2.repository;


import com.hadleynet.ExpenseTrackerV2.model.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends MongoRepository<Expense, Long> {
    List<Expense> findByName(String name);
}
