package com.example.demo.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "account_from")
    private Integer accountFrom;

    @Column(name = "account_to")
    private Integer accountTo;

    @Column(name = "currency_shortname")
    private String currencyShortname;

    @Column(name = "summary")
    private Float summary;

    @Column(name = "expense_category")
    private Category expenseCategory;

    @Column(name = "datetime")
    private Timestamp datetime;

    @Column(name = "limit_exceeded")
    private Boolean limitExceeded;

    public Transaction(Integer accountFrom, Integer accountTo, String currencyShortname, Float summary, Category expenseCategory, Timestamp datetime) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.currencyShortname = currencyShortname;
        this.summary = summary;
        this.expenseCategory = expenseCategory;
        this.datetime = datetime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(Integer accountFrom) {
        this.accountFrom = accountFrom;
    }

    public Integer getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(Integer accountTo) {
        this.accountTo = accountTo;
    }

    public String getCurrencyShortname() {
        return currencyShortname;
    }

    public void setCurrencyShortname(String currencyShortname) {
        this.currencyShortname = currencyShortname;
    }

    public Float getSummary() {
        return summary;
    }

    public void setSummary(Float summary) {
        this.summary = summary;
    }

    public Category getExpenseCategory() {
        return expenseCategory;
    }

    public void setExpenseCategory(Category expenseCategory) {
        this.expenseCategory = expenseCategory;
    }

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    public Boolean getLimitExceeded() {
        return limitExceeded;
    }

    public void setLimitExceeded(Boolean limitExceeded) {
        this.limitExceeded = limitExceeded;
    }
}
