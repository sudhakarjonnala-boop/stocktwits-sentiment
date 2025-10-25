package com.example.stockapp.repository;

import com.example.stockapp.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
    Stock findBySymbol(String symbol);
}
