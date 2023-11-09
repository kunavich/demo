package com.example.demo.contorller;

import com.example.demo.dto.BusinessEntityAllLimitsDto;
import com.example.demo.dto.BusinessEntityDto;
import com.example.demo.dto.BusinessEntityLimitDto;
import com.example.demo.entity.BusinessEntity;
import com.example.demo.entity.Category;
import com.example.demo.service.BusinessEntityServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/business")
@Slf4j
public class BusinessEntityController {

    @Autowired
    private BusinessEntityServiceImpl businessEntityService;

    @PostMapping("/setLimit")
    public void setLimit(@RequestBody BusinessEntityLimitDto limitDto) {
        log.info("Set Business Entity Limit");
        Category cat = Category.valueOf(limitDto.getCategory());
        businessEntityService.setLimit(limitDto.getAccount(),limitDto.getLimit(),cat);
    }

    @PostMapping("/")
    public void newBusinessEntity(@RequestBody BusinessEntityDto businessEntityDto) {
        log.info("Save Business Entity");
        businessEntityService.save(new BusinessEntity( businessEntityDto.getName(),businessEntityDto.getAccount()));
    }

    @GetMapping("/accountLimits")
    public BusinessEntityAllLimitsDto getAccountLimits(Integer account) {
        log.info("Get Account Limits");
        BusinessEntityAllLimitsDto businessEntityAllLimitsDto = new BusinessEntityAllLimitsDto(businessEntityService.findByAccount(account));
        return businessEntityAllLimitsDto;
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
