package com.example.demo.contorller;

import com.example.demo.dto.TransactionDto;
import com.example.demo.dto.TransactionWithLimitsDto;
import com.example.demo.service.TransactionServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
@Slf4j
public class TransactionController {

    @Autowired
    private TransactionServiceImpl transactionService;

    @GetMapping("/limitExceededList")
    public List<TransactionWithLimitsDto> getLimitExceededList() {
        log.info("Get Limit Exceeded List");
        return transactionService.findAllWithLimitExceeded();
    }

    @PostMapping("/")
    public void newTransaction(@RequestBody TransactionDto transactionDto) {
        log.info("Save Transaction");
        transactionService.save(transactionDto);
    }

}
