package com.example.demo.service;

import com.example.demo.dao.TransactionRepository;
import com.example.demo.entity.BusinessEntity;
import com.example.demo.entity.Category;
import com.example.demo.entity.ExchangeRate;
import com.example.demo.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionServiceImpl implements TempoService<Transaction> {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BusinessEntityServiceImpl businessEntityService;

    @Autowired
    private ExchangeRateServiceImpl exchangeRateService;

    @Override
    @Transactional
    public Transaction save(Transaction transaction) {

        ExchangeRate exchangeRate = exchangeRateService.findBySymbol(transaction.getCurrencyShortname());
        BusinessEntity businessEntity = businessEntityService.findByAccount(transaction.getAccountFrom());

        LocalDate dateOfSum = businessEntity.getDateOfSum().toLocalDateTime().toLocalDate();
        LocalDate currentDate = LocalDate.now();
        if ( dateOfSum.getMonth() == currentDate.getMonth() && dateOfSum.getYear() != currentDate.getYear()) {
            businessEntity.setDateOfSum( new Timestamp(System.currentTimeMillis()));
            if (transaction.getExpenseCategory().equals(Category.PRODUCT)) {
                businessEntity.setSumOfServices(0f);
            } else {
                businessEntity.setSumOfServices(0f);
            }
        }

        Integer limit;
        Float sumUSD = transaction.getSummary() / exchangeRate.getRate();

        if (transaction.getExpenseCategory().equals(Category.PRODUCT)) {
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

    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        transactionRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        transactionRepository.deleteAll();
    }

    public List<Transaction> findAllWithLimitExceeded() {
        return transactionRepository.findAllWithLimitExceeded();
    }
}
