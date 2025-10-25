package com.example.stockapp.controller;

import com.example.stockapp.entity.StockTwitsSentiment;
import com.example.stockapp.service.StockTwitsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/stocktwits")
@RequiredArgsConstructor
public class StockTwitsController {

    private final StockTwitsService service;

    @GetMapping("/update")
    public String updateSentiments() {
        service.updateStockTwitsSentiments();
        return "StockTwits data updated successfully.";
    }

    @GetMapping("/all")
    public List<StockTwitsSentiment> getAll() {
        return service.getAllSentiments();
    }
}
