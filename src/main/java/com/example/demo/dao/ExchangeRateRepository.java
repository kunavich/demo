package com.example.demo.dao;

import com.example.demo.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate,Integer> {

    ExchangeRate findBySymbol(String symbol);
}
