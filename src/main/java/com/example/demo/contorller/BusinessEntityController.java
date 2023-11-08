package com.example.demo.contorller;

import com.example.demo.entity.BusinessEntity;
import com.example.demo.entity.Category;
import com.example.demo.service.BusinessEntityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/business")
public class BusinessEntityController {

    @Autowired
    private BusinessEntityServiceImpl businessEntityService;

    @PostMapping("/setLimit")
    public void setLimit(int account, int limit,String category) {
        Category cat = Category.valueOf(category);
        businessEntityService.setLimit(account,limit,cat);
    }

    @PostMapping("/")
    public void newBusinessEntity(BusinessEntity businessEntity) {
        businessEntityService.save(businessEntity);
    }

}
