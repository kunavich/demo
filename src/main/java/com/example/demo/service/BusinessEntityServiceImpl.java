package com.example.demo.service;

import com.example.demo.dao.BusinessEntityRepository;
import com.example.demo.entity.BusinessEntity;
import com.example.demo.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
public class BusinessEntityServiceImpl implements TempoService<BusinessEntity> {

    @Autowired
    private BusinessEntityRepository businessEntityRepository;

    @Override
    @Transactional
    public BusinessEntity save(BusinessEntity businessEntity) {
        return businessEntityRepository.save(businessEntity);
    }

    public List<BusinessEntity> findAll() {
        return businessEntityRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        businessEntityRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        businessEntityRepository.deleteAll();
    }

    public void setLimit(int account, int limit, Category category) {
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

    public BusinessEntity findByAccount(Integer account) {
        return businessEntityRepository.findByAccount(account);
    }

}
