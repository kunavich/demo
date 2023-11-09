package com.example.demo.contorller;

import com.example.demo.dto.TransactionDto;
import com.example.demo.dto.TransactionWithLimitsDto;
import com.example.demo.service.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionServiceImpl transactionService;

    @GetMapping("/limitExceededList")
    public List<TransactionWithLimitsDto> getlimitExceededList() {
        List<TransactionWithLimitsDto> list=transactionService.findAllWithLimitExceeded();
        return list;
    }

    @PostMapping("/")
    public void newTransaction(@RequestBody TransactionDto transactionDto) {
        transactionService.save(transactionDto);
    }

}
