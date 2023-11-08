package com.example.demo.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "business_entity")
public class BusinessEntity {

    public static final int DEFAULT_LIMIT = 1000;
    public static final float DEFAULT_SUM = 0f;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "account")
    private Integer account;

    @Column(name = "limit_of_goods")
    private Integer limitOfGoods;

    @Column(name = "limit_of_services")
    private Integer limitOfServices;

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

    public BusinessEntity(String name, Integer account) {
        this.name = name;
        this.account = account;
        this.limitOfGoods = DEFAULT_LIMIT;
        this.limitOfServices = DEFAULT_LIMIT;

        this.sumOfGoods = DEFAULT_SUM;
        this.sumOfServices = DEFAULT_SUM;
        this.dateOfSum = new Timestamp(System.currentTimeMillis());
    }

    public BusinessEntity(String name, Integer account, Category limitCategory, Integer limit) {
        this.name = name;
        this.account = account;
        if (limitCategory.equals(Category.PRODUCT)) {
            this.limitOfGoods = limit;
            this.limitOfServices = DEFAULT_LIMIT;
            this.dateOfGoodsLimit = new Timestamp(System.currentTimeMillis());
        } else {
            this.limitOfGoods = DEFAULT_LIMIT;
            this.limitOfServices = limit;
            this.dateOfServicesLimit = new Timestamp(System.currentTimeMillis());
        }

        this.sumOfGoods = DEFAULT_SUM;
        this.sumOfServices = DEFAULT_SUM;
        this.dateOfSum = new Timestamp(System.currentTimeMillis());
    }

    public BusinessEntity(String name, Integer account, Integer limitOfGoods, Integer limitOfServices) {
        this.name = name;
        this.account = account;
        this.limitOfGoods = limitOfGoods;
        this.limitOfServices = limitOfServices;
        this.dateOfGoodsLimit = new Timestamp(System.currentTimeMillis());
        this.dateOfServicesLimit = new Timestamp(System.currentTimeMillis());

        this.sumOfGoods = DEFAULT_SUM;
        this.sumOfServices = DEFAULT_SUM;
        this.dateOfSum = new Timestamp(System.currentTimeMillis());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    public Integer getLimitOfGoods() {
        return limitOfGoods;
    }

    public void setLimitOfGoods(Integer limitOfGoods) {
        this.limitOfGoods = limitOfGoods;
    }

    public Integer getLimitOfServices() {
        return limitOfServices;
    }

    public void setLimitOfServices(Integer limitOfServices) {
        this.limitOfServices = limitOfServices;
    }

    public Timestamp getDateOfServicesLimit() {
        return dateOfServicesLimit;
    }

    public void setDateOfServicesLimit(Timestamp dateOfServicesLimit) {
        this.dateOfServicesLimit = dateOfServicesLimit;
    }

    public Timestamp getDateOfGoodsLimit() {
        return dateOfGoodsLimit;
    }

    public void setDateOfGoodsLimit(Timestamp dateOfGoodsLimit) {
        this.dateOfGoodsLimit = dateOfGoodsLimit;
    }

    public Float getSumOfServices() {
        return sumOfServices;
    }

    public void setSumOfServices(Float sumOfServices) {
        this.sumOfServices = sumOfServices;
    }

    public Float getSumOfGoods() {
        return sumOfGoods;
    }

    public void setSumOfGoods(Float sumOfGoods) {
        this.sumOfGoods = sumOfGoods;
    }

    public Timestamp getDateOfSum() {
        return dateOfSum;
    }

    public void setDateOfSum(Timestamp dateOfSum) {
        this.dateOfSum = dateOfSum;
    }
}
