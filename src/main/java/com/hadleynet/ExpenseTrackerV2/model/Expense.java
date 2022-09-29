package com.hadleynet.ExpenseTrackerV2.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Document
public class Expense {
    @Id

    private long id;

    @CreatedDate
    private Date createdDate;

    @LastModifiedDate
    private Date lastModifiedDate;

    private String name;
    private String description;
    private BigDecimal amount;
    private User user;

    public Expense(String name, String description, BigDecimal amount, User user) {
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.user = user;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expense expense = (Expense) o;
        return id == expense.id && Objects.equals(createdDate, expense.createdDate) && Objects.equals(lastModifiedDate, expense.lastModifiedDate) && Objects.equals(name, expense.name) && Objects.equals(description, expense.description) && Objects.equals(amount, expense.amount) && Objects.equals(user, expense.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdDate, lastModifiedDate, name, description, amount, user);
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", createdDate=" + createdDate +
                ", lastModifiedDate=" + lastModifiedDate +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", user=" + user +
                '}';
    }
}
