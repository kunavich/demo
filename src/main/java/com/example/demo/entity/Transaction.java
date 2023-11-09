package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
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
}
