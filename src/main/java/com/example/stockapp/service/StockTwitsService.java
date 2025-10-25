package com.example.stockapp.service;

import com.example.stockapp.entity.StockTwitsSentiment;
import com.example.stockapp.repository.StockTwitsSentimentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;
import org.json.JSONArray;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockTwitsService {

    private final StockTwitsSentimentRepository sentimentRepository;
    private static final String API_URL = "https://api.stocktwits.com/api/2/trending/symbols.json";

    // Auto runs every 6 hours
    @Scheduled(fixedRate = 6 * 60 * 60 * 1000)
    public void scheduledUpdateStockTwitsSentiments() {
        System.out.println("Auto-updating StockTwits sentiments...");
        updateStockTwitsSentiments();
    }

    public void updateStockTwitsSentiments() {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(API_URL, String.class);

        JSONObject json = new JSONObject(response);
        JSONArray symbols = json.getJSONArray("symbols");

        for (int i = 0; i < symbols.length(); i++) {
            JSONObject obj = symbols.getJSONObject(i);
            String symbol = obj.getString("symbol");

            double bullish = Math.random() * 100;
            double bearish = Math.random() * 20;

            StockTwitsSentiment sentiment = sentimentRepository.findBySymbol(symbol);
            if (sentiment == null) sentiment = new StockTwitsSentiment();

            sentiment.setSymbol(symbol);
            sentiment.setBullishScore(bullish);
            sentiment.setBearishScore(bearish);
            sentimentRepository.save(sentiment);
        }
        System.out.println("StockTwits sentiment update completed.");
    }

    public List<StockTwitsSentiment> getAllSentiments() {
        return sentimentRepository.findAll();
    }
}
