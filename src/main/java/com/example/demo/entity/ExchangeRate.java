package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.AllArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "exchange_rate")
@Getter
@AllArgsConstructor
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

}
