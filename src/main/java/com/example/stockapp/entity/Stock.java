package com.example.stockapp.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "stocks")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String symbol;

    private String companyName;
}
