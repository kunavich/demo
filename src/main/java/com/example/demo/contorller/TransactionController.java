package com.example.demo.contorller;

import com.example.demo.entity.Transaction;
import com.example.demo.service.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionServiceImpl transactionService;

    @GetMapping("/limitExceededList")
    public List<Transaction> getlimitExceededList() {
        return transactionService.findAllWithLimitExeeded();
    }

    //TODO
    //-------------------
    @GetMapping("/1")
    public String hello() {
        return "go";
    }

    @PostMapping("/")
    public void newTransaction(Transaction transaction) {
        transactionService.save(transaction);
    }

}
