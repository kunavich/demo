package com.example.demo.service;

import com.example.demo.dao.BusinessEntityRepository;
import com.example.demo.entity.BusinessEntity;
import com.example.demo.entity.Category;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
@Slf4j
public class BusinessEntityServiceImpl {

    @Autowired
    private BusinessEntityRepository businessEntityRepository;

    @Transactional
    public BusinessEntity save(BusinessEntity businessEntity) {
        return businessEntityRepository.save(businessEntity);
    }

    public List<BusinessEntity> findAll() {
        return businessEntityRepository.findAll();
    }

    @Transactional
    public void deleteById(Integer id) {
        businessEntityRepository.deleteById(id);
    }

    @Transactional
    public void deleteAll() {
        businessEntityRepository.deleteAll();
    }

    public void setLimit(String account, float limit, Category category) {
        BusinessEntity businessEntity = findByAccount(account);
        if (category.equals(Category.PRODUCT)) {
            if(businessEntity.getDateOfGoodsLimit() != null){
                throw new RuntimeException("It is forbidden to update existing limits");
            }
            businessEntity.setLimitOfGoods(limit);
            businessEntity.setDateOfGoodsLimit(new Timestamp(System.currentTimeMillis()));
        } else {
            if(businessEntity.getDateOfServicesLimit() != null){
                throw new RuntimeException("It is forbidden to update existing limits");
            }
            businessEntity.setLimitOfServices(limit);
            businessEntity.setDateOfServicesLimit(new Timestamp(System.currentTimeMillis()));
        }
        save(businessEntity);
    }

    public BusinessEntity findByAccount(String account) {
        return businessEntityRepository.findByAccount(account);
    }

}
