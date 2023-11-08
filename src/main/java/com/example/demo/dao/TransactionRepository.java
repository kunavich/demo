package com.example.demo.dao;

import com.example.demo.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

    @Query(value = "SELECT * FROM transactions t WHERE t.limit_exceeded != 0",nativeQuery = true)
    List<Transaction> findAllWithLimitExceeded();

}
