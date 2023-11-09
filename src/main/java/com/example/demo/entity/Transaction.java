package com.example.demo.entity;

import com.example.demo.dto.TransactionDto;
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

    //TODO leading zero not allowed && out of range ex 999999999
    @Column(name = "account_from")
    private Integer accountFrom;

    //TODO leading zero not allowed && out of range ex 999999999
    @Column(name = "account_to")
    private Integer accountTo;

    @Column(name = "currency_shortname")
    private String currencyShortname;

    @Column(name = "summary")
    private Float summary;

    @Column(name = "expense_category")
    private String expenseCategory;

    @Column(name = "datetime")
    private Timestamp datetime;

    @Column(name = "limit_exceeded")
    private Boolean limitExceeded;

    public Transaction(Integer accountFrom, Integer accountTo, String currencyShortname, Float summary, String expenseCategory, Timestamp datetime) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.currencyShortname = currencyShortname;
        this.summary = summary;
        this.expenseCategory = expenseCategory;
        this.datetime = datetime;
    }

    public Transaction(TransactionDto transactionDto) {
        this.accountFrom = transactionDto.getAccountFrom();
        this.accountTo = transactionDto.getAccountTo();
        this.currencyShortname = transactionDto.getCurrencyShortname();
        this.summary = transactionDto.getSummary();
        this.expenseCategory = transactionDto.getExpenseCategory();
        this.datetime = Timestamp.valueOf(transactionDto.getDatetime());
    }
}
