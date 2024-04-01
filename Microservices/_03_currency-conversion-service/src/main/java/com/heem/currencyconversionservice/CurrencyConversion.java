package com.heem.currencyconversionservice;

import java.math.BigDecimal;

public class CurrencyConversion {
    private Long id;
    private String from;
    private String to;
    private Double conversionMultiple;
    private Integer quantity;
    private Double totalCalcualtedAmount;
    private String environment;

    public CurrencyConversion() {
    }

    public CurrencyConversion(Long id, String from, String to, Double conversionMultiple, Integer quantity, Double totalCalcualtedAmount, String environment) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.conversionMultiple = conversionMultiple;
        this.quantity = quantity;
        this.totalCalcualtedAmount = totalCalcualtedAmount;
        this.environment = environment;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Double getConversionMultiple() {
        return conversionMultiple;
    }

    public void setConversionMultiple(Double conversionMultiple) {
        this.conversionMultiple = conversionMultiple;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotalCalcualtedAmount() {
        return totalCalcualtedAmount;
    }

    public void setTotalCalcualtedAmount(Double totalCalcualtedAmount) {
        this.totalCalcualtedAmount = totalCalcualtedAmount;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }
}
