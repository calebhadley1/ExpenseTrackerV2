package com.hadleynet.ExpenseTrackerV2.model;

import java.math.BigDecimal;
import java.util.Objects;

public class ExpenseDto {
    private long id;
    private String name;
    private String description;
    private BigDecimal amount;
    private long appUserId;

    private ExpenseDto() {
    }

    public ExpenseDto(long id, String name, String description, BigDecimal amount, long appUserId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.appUserId = appUserId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public long getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(long appUserId) {
        this.appUserId = appUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExpenseDto that = (ExpenseDto) o;
        return id == that.id && appUserId == that.appUserId && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, amount, appUserId);
    }
}
