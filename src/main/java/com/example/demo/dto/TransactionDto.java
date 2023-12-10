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
}
