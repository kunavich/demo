package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "exchange_rate")
@Getter
@Setter
@NoArgsConstructor
public class ExchangeRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "rate")
    private float rate;

    //TODO UNix time to normal
    @Column(name = "rate_timestamp")
    private Timestamp timestamp;

    public ExchangeRate(String symbol, float rate, Timestamp timestamp) {
        this.symbol = symbol;
        this.rate = rate;
        this.timestamp = timestamp;
    }

}
