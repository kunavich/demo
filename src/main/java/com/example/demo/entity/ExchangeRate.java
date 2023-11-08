package com.example.demo.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "exchange_rate")
public class ExchangeRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "rate")
    private float rate;

    @Column(name = "rate_timestamp")
    private Timestamp timestamp;

    public ExchangeRate(String symbol, float rate, Timestamp timestamp) {
        this.symbol = symbol;
        this.rate = rate;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public String getSymbol() {
        return symbol;
    }

    public float getRate() {
        return rate;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }
}
