package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionDto {
    private String accountFrom;
    private String accountTo;
    private String currencyShortname;
    private float summary;
    private String expenseCategory;
    private String datetime;

    public TransactionDto(Transaction transaction) {
        this.accountFrom = transaction.getAccountFrom();
        this.accountTo = transaction.getAccountTo();
        this.currencyShortname = transaction.getCurrencyShortname();
        this.summary = transaction.getSummary();
        this.expenseCategory = transaction.getExpenseCategory();
        this.datetime = String.valueOf(transaction.getDatetime());
    }
}
