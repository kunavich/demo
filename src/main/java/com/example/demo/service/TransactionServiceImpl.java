package com.example.demo.service;

import com.example.demo.dao.TransactionRepository;
import com.example.demo.dto.TransactionDto;
import com.example.demo.dto.TransactionWithLimitsDto;
import com.example.demo.entity.BusinessEntity;
import com.example.demo.entity.Category;
import com.example.demo.entity.ExchangeRate;
import com.example.demo.entity.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TransactionServiceImpl  {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BusinessEntityServiceImpl businessEntityService;

    @Autowired
    private ExchangeRateServiceImpl exchangeRateService;

    @Transactional
    public Transaction save(TransactionDto transactionDto) {
        Transaction transaction = new Transaction(transactionDto);

        ExchangeRate exchangeRate = exchangeRateService.findBySymbol(transaction.getCurrencyShortname());
        BusinessEntity businessEntity = businessEntityService.findByAccount(transaction.getAccountFrom());

        businessEntity = checkSumDate( businessEntity);

        Float limit;
        Float sumUSD = transaction.getSummary() / exchangeRate.getRate();

        if (Category.valueOf(transaction.getExpenseCategory()).equals(Category.PRODUCT)) {
            limit = businessEntity.getLimitOfGoods();
            sumUSD += businessEntity.getSumOfGoods();
            businessEntity.setSumOfGoods(sumUSD);
        } else {
            limit = businessEntity.getLimitOfServices();
            sumUSD += businessEntity.getSumOfServices();
            businessEntity.setSumOfServices(sumUSD);
        }

        transaction.setLimitExceeded(limit < sumUSD);
        businessEntityService.save(businessEntity);
        return transactionRepository.save(transaction);
    }

    private BusinessEntity checkSumDate( BusinessEntity businessEntity) {
        LocalDate dateOfSum = businessEntity.getDateOfSum().toLocalDateTime().toLocalDate();
        LocalDate currentDate = LocalDate.now();
        if ( dateOfSum.getMonth() != currentDate.getMonth() && dateOfSum.getYear() != currentDate.getYear()) {
            businessEntity.setDateOfSum( new Timestamp(System.currentTimeMillis()));
            businessEntity.setSumOfServices(0f);
            businessEntity.setSumOfServices(0f);
        }
        return businessEntity;
    }

    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Transactional
    public void deleteById(Integer id) {
        transactionRepository.deleteById(id);
    }

    @Transactional
    public void deleteAll() {
        transactionRepository.deleteAll();
    }


    /* TODO use JOIN
    SELECT t.account_from, t.account_to, t.currency_shortname, t.summary, t.expense_category, t.datetime, b.limit_of_goods,
     b.date_of_goods_limit, b.limit_of_services, b.date_of_services_limit FROM transactions t
     INNER JOIN business_entity b ON t.account_from=b.account WHERE t.limit_exceeded != 0;"
     */
    public List<TransactionWithLimitsDto> findAllWithLimitExceeded() {
        List<TransactionWithLimitsDto> list = new ArrayList<>();

        transactionRepository.findAllWithLimitExceeded().forEach( e -> {
            BusinessEntity businessEntity = businessEntityService.findByAccount(e.getAccountFrom());
            list.add(new TransactionWithLimitsDto(e,businessEntity));
        });
        return list;
    }
}
