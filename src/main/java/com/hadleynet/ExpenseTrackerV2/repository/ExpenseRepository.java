package com.hadleynet.ExpenseTrackerV2.repository;


import com.hadleynet.ExpenseTrackerV2.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    Optional<List<Expense>> findByName(String name);

    List<Expense> findByAppUserId(long app_user_id);
}
