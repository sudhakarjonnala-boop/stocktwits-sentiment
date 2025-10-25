package com.example.stockapp.repository;

import com.example.stockapp.entity.StockTwitsSentiment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockTwitsSentimentRepository extends JpaRepository<StockTwitsSentiment, Long> {
    StockTwitsSentiment findBySymbol(String symbol);
}
