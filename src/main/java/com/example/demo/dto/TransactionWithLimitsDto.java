package com.example.demo.dto;

import com.example.demo.entity.BusinessEntity;
import com.example.demo.entity.Category;
import com.example.demo.entity.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionWithLimitsDto {
    public static final String LIMIT_CURRENCY_SHORTNAME = "USD";

    private String accountFrom;
    private String accountTo;
    private String currencyShortname;
    private float summary;
    private String expenseCategory;
    private String datetime;

    private float limitSum;
    private String limit_datetime;
    private String limit_currency_shortname;

    public TransactionWithLimitsDto(Transaction transaction, BusinessEntity businessEntity) {
        this.accountFrom = transaction.getAccountFrom();
        this.accountTo = transaction.getAccountTo();
        this.currencyShortname = transaction.getCurrencyShortname();
        this.summary = transaction.getSummary();
        this.expenseCategory = transaction.getExpenseCategory();
        this.datetime = transaction.getDatetime().toString();

        if(Category.PRODUCT.equals(Category.valueOf(expenseCategory))) {
            this.limitSum = businessEntity.getLimitOfGoods();
            this.limit_datetime = businessEntity.getDateOfGoodsLimit().toString();
        } else {
            this.limitSum = businessEntity.getLimitOfServices();
            this.limit_datetime = businessEntity.getDateOfServicesLimit().toString();
        }
        this.limit_currency_shortname = LIMIT_CURRENCY_SHORTNAME;
    }
}
