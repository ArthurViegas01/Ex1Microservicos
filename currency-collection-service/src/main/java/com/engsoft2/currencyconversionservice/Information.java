package com.engsoft2.currencyconversionservice;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Information {
    private String currency;
    private BigDecimal quantity;
    private BigDecimal value;
    private LocalDateTime timestamp;
    public Information(String currency, BigDecimal quantity, BigDecimal value, LocalDateTime timestamp) {
        this.currency = currency;
        this.quantity = quantity;
        this.value = value;
        this.timestamp = timestamp;
    }
    public Information(){}
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public BigDecimal getQuantity() {
        return quantity;
    }
    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }
    public BigDecimal getValue() {
        return value;
    }
    public void setValue(BigDecimal value) {
        this.value = value;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    
    

    
}
