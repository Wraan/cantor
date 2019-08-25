package com.wran.cantor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class CurrencyRatesWebsocketDto implements Serializable {

    @JsonProperty("Name")
    private String name;
    @JsonProperty("Code")
    private String code;
    @JsonProperty("Unit")
    private int unit;
    @JsonProperty("PurchasePrice")
    private double purchasePrice;
    @JsonProperty("SellPrice")
    private double sellPrice;
    @JsonProperty("AveragePrice")
    private double averagePrice;

    public CurrencyRatesWebsocketDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(double averagePrice) {
        this.averagePrice = averagePrice;
    }
}
