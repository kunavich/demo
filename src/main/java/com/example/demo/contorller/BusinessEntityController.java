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
        businessEntityService.setLimit(limitDto.getAccount(),limitDto.getLimit(),Category.valueOf(limitDto.getCategory()));
    }

    @PostMapping("/")
    public void newBusinessEntity(@RequestBody BusinessEntityDto businessEntityDto) {
        log.info("Save Business Entity");
        businessEntityService.save(new BusinessEntity( businessEntityDto.getName(),businessEntityDto.getAccount()));
    }

    @GetMapping("/accountLimits")
    public BusinessEntityAllLimitsDto getAccountLimits(String account) {
        log.info("Get Account Limits");
        return new BusinessEntityAllLimitsDto(businessEntityService.findByAccount(account));
    }

}
