package com.example.demo.dao;

import com.example.demo.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

    @Query("SELECT * FROM transactions WHERE limit_exceeded !=0")
    List<Transaction> findAllWithLimitExceeded();

}
