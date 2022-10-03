package com.hadleynet.ExpenseTrackerV2.repository;


import com.hadleynet.ExpenseTrackerV2.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByName(String name);
}
