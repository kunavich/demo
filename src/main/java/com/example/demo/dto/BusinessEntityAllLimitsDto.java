package com.example.demo.dto;

import com.example.demo.entity.BusinessEntity;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class BusinessEntityAllLimitsDto {
    private String name;
    private String account;
    private Float limitOfGoods;
    private Float limitOfServices;

    public BusinessEntityAllLimitsDto (BusinessEntity businessEntity) {
        this.account=businessEntity.getAccount();
        this.name=businessEntity.getName();
        this.limitOfGoods=businessEntity.getLimitOfGoods();
        this.limitOfServices=businessEntity.getLimitOfServices();
    }
}
