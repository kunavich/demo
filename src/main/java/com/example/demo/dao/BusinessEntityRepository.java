package com.example.demo.dao;

import com.example.demo.entity.BusinessEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessEntityRepository extends JpaRepository<BusinessEntity,Integer> {

    BusinessEntity findByAccount(Integer account);
}
