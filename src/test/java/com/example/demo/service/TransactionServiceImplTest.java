package com.example.demo.service;

import com.example.demo.entity.BusinessEntity;
import org.junit.Test;
import org.mockito.Mock;

import java.sql.Timestamp;

import static org.junit.Assert.assertEquals;

public class TransactionServiceImplTest {

    @Mock private TransactionServiceImpl transactionService;

    @Test
    public void whenDateIsTheSameCheckSumDateTest() {
        BusinessEntity root = new BusinessEntity("roga i kopyta",1000000123);
        BusinessEntity businessEntity = new BusinessEntity("roga i kopyta",1000000123);

        root.setDateOfSum(new Timestamp(System.currentTimeMillis()));
        businessEntity.setDateOfSum(new Timestamp(System.currentTimeMillis()));
        //transactionService.c

        assertEquals(root, businessEntity);

    }
    @Test
    public void whenDateIsNotSameCheckSumDateTest() {
        BusinessEntity root = new BusinessEntity("roga i kopyta",1000000123);
        BusinessEntity businessEntity = new BusinessEntity("roga i kopyta",1000000123);

        root.setDateOfSum(new Timestamp(System.currentTimeMillis()));
        businessEntity.setDateOfSum(new Timestamp(1663027200000L));

        assertEquals(root, businessEntity);

    }
}
