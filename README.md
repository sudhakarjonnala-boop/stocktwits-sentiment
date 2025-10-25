# 📊 StockTwits Sentiment Auto-Updater (Spring Boot + MySQL)

This Spring Boot application automatically fetches **trending bullish stocks** from the [StockTwits public API](https://api.stocktwits.com/api/2/trending/symbols.json), stores the data in **MySQL**, and refreshes it every **6 hours** using Spring’s `@Scheduled` task.

---

## 🚀 Features
- Automatically pulls trending stock symbols from StockTwits
- Stores bullish/bearish sentiment scores in MySQL
- Auto-updates every 6 hours
- REST API endpoints for manual refresh and data viewing
- Simple join-ready schema for combining with existing stock master table

---

## 🗄️ Database Schema (MySQL)

```sql
CREATE TABLE stocks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    symbol VARCHAR(20) NOT NULL UNIQUE,
    company_name VARCHAR(100)
);

CREATE TABLE stocktwits_sentiment (
    id INT AUTO_INCREMENT PRIMARY KEY,
    symbol VARCHAR(20) NOT NULL,
    bullish_score DECIMAL(5,2),
    bearish_score DECIMAL(5,2),
    last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (symbol) REFERENCES stocks(symbol)
);
```

---

## ⚙️ Setup Instructions

### 1️⃣ Clone or Download
Download or clone this project:
```bash
git clone https://github.com/sudhakarjonnala-boop/stocktwits-sentiment.git
cd stocktwits-sentiment
```

### 2️⃣ Configure Database
Edit `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/stockdb?useSSL=false
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 3️⃣ Build & Run
```bash
mvn spring-boot:run
```

---

## 🕒 Auto-Scheduler

The service runs every **6 hours**:
```java
@Scheduled(fixedRate = 6 * 60 * 60 * 1000)
public void scheduledUpdateStockTwitsSentiments() { ... }
```

You can also trigger it manually:
```
GET http://localhost:8080/api/stocktwits/update
```

---

## 🔍 REST Endpoints

| Endpoint | Method | Description |
|-----------|---------|-------------|
| `/api/stocktwits/update` | GET | Manually update StockTwits data |
| `/api/stocktwits/all` | GET | Get all sentiment records |

---

## 🧠 Example Join Query

```sql
SELECT s.symbol, s.company_name, t.bullish_score, t.bearish_score
FROM stocks s
JOIN stocktwits_sentiment t ON s.symbol = t.symbol
ORDER BY t.bullish_score DESC;
```

---

## 🧰 Tech Stack
- **Spring Boot 3**
- **MySQL**
- **Spring Data JPA**
- **Lombok**
- **StockTwits API**
- **JSON.org**

---

## 🧑‍💻 Author
**Sudhakar Jonnala**  
GitHub: [@sudhakarjonnala-boop](https://github.com/sudhakarjonnala-boop)

---
