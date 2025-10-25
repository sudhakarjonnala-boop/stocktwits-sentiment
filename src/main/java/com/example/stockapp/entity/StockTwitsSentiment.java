package com.example.stockapp.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "stocktwits_sentiment")
public class StockTwitsSentiment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String symbol;
    private Double bullishScore;
    private Double bearishScore;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private java.sql.Timestamp lastUpdated;
}
