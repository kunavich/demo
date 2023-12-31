package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "business_entity")
@Getter
@Setter
@NoArgsConstructor
public class BusinessEntity {

    public static final Float DEFAULT_LIMIT = 1000f;
    public static final float DEFAULT_SUM = 0f;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "account")
    private String account;

    @Column(name = "limit_of_goods")
    private Float limitOfGoods;

    @Column(name = "limit_of_services")
    private Float limitOfServices;

    @Column(name = "date_of_services_limit")
    private Timestamp dateOfServicesLimit;

    @Column(name = "date_of_goods_limit")
    private Timestamp dateOfGoodsLimit;

    @Column(name = "sum_of_services")
    private Float sumOfServices;

    @Column(name = "sum_of_goods")
    private Float sumOfGoods;

    @Column(name = "date_of_sum")
    private Timestamp dateOfSum;

    public BusinessEntity(String name, String account) {
        this.name = name;
        this.account = account;
        this.limitOfGoods = DEFAULT_LIMIT;
        this.limitOfServices = DEFAULT_LIMIT;

        this.sumOfGoods = DEFAULT_SUM;
        this.sumOfServices = DEFAULT_SUM;
        this.dateOfSum = new Timestamp(System.currentTimeMillis());
    }
}
