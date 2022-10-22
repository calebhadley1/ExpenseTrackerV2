package com.hadleynet.ExpenseTrackerV2.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity(name = "Expense")
@Table(name = "expense")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;
    @Column(name="description")
    private String description;
    @Column(name="amount")
    private BigDecimal amount;
    @ManyToOne
    @JoinColumn(name = "app_user_id", nullable = false)
    private AppUser appUser;

    public Expense(String name, String description, BigDecimal amount, AppUser appUser) {
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.appUser = appUser;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expense expense = (Expense) o;
        return id == expense.id && Objects.equals(name, expense.name) && Objects.equals(description, expense.description) && Objects.equals(amount, expense.amount) && Objects.equals(appUser, expense.appUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, amount, appUser);
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", appUser=" + appUser +
                '}';
    }

    public Long getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public AppUser getAppUser() {
        return this.appUser;
    }

    public String getDescription() {
        return this.description;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }
}
