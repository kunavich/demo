package com.example.demo.contorller;

import com.example.demo.dto.BusinessEntityDto;
import com.example.demo.dto.BusinessEntityLimitDto;
import com.example.demo.entity.BusinessEntity;
import com.example.demo.entity.Category;
import com.example.demo.service.BusinessEntityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/business")
public class BusinessEntityController {

    @Autowired
    private BusinessEntityServiceImpl businessEntityService;

    @PostMapping("/setLimit")
    public void setLimit(@RequestBody BusinessEntityLimitDto limitDto) {
        Category cat = Category.valueOf(limitDto.getCategory());
        businessEntityService.setLimit(limitDto.getAccount(),limitDto.getLimit(),cat);
    }

    @PostMapping("/")
    public void newBusinessEntity(@RequestBody BusinessEntityDto businessEntityDto) {
        businessEntityService.save(new BusinessEntity( businessEntityDto.getName(),businessEntityDto.getAccount()));
    }

    /*
    @PostMapping("/")
    public void newBusinessEntity(String name, Integer account,String limitCategory, Integer limit) {
        Category category = Category.valueOf(limitCategory);
        businessEntityService.save(new BusinessEntity( name,account,category,limit));
    }

    @PostMapping("/")
    public void newBusinessEntity(String name, Integer account, Integer limitOfGoods, Integer limitOfServices) {
        businessEntityService.save(new BusinessEntity( name,account,limitOfGoods,limitOfServices));
    }

     */

}
