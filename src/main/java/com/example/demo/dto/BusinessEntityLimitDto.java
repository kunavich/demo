package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BusinessEntityLimitDto {

    private String account;
    private int limit;
    private String category;
}
