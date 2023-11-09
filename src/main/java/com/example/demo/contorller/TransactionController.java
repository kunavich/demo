package com.example.demo.contorller;

import com.example.demo.dto.TransactionDto;
import com.example.demo.entity.Category;
import com.example.demo.entity.Transaction;
import com.example.demo.service.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionServiceImpl transactionService;

    @GetMapping("/limitExceededList")
    public List<Transaction> getlimitExceededList() {
        List<Transaction> list=transactionService.findAllWithLimitExceeded();
        return list;
    }

    @PostMapping("/")
    public void newTransaction(@RequestBody TransactionDto transactionDto) {
        Category category = Category.valueOf(transactionDto.getExpenseCategory());
        Timestamp timestamp = Timestamp.valueOf(transactionDto.getDatetime());
        transactionService.save(new Transaction(transactionDto.getAccountFrom(),transactionDto.getAccountTo(),
                transactionDto.getCurrencyShortname(),transactionDto.getSummary(),category,timestamp));
    }

}
