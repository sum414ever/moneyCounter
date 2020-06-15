package com.simplemoneycounter.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Cost {

    public String userId;

    public LocalDateTime localDateTime;

    public String currency;

    public BigDecimal amount;

    public String category;

    public String description;

    public Cost() {
    }

    public Cost(String userId, LocalDateTime localDateTime, String currency, BigDecimal amount, String category, String description) {
        this.userId = userId;
        this.localDateTime = localDateTime;
        this.currency = currency;
        this.amount = amount;
        this.category = category;
        this.description = description;
    }
}
