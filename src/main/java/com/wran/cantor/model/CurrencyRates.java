package com.wran.cantor.model;

import javax.persistence.*;

@Entity
@Table(name = "currency_rates")
public class CurrencyRates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private int unit;

    @Column(name = "purchase_value")
    private float purchaseValue;
    @Column(name = "sell_value")
    private float sellValue;
    @Column(name = "average_value")
    private float averageValue;

    public CurrencyRates() {
    }

    public CurrencyRates(String code, int unit, float purchaseValue, float sellValue, float averageValue) {
        this.code = code;
        this.unit = unit;
        this.purchaseValue = purchaseValue;
        this.sellValue = sellValue;
        this.averageValue = averageValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public float getPurchaseValue() {
        return purchaseValue;
    }

    public void setPurchaseValue(float purchaseValue) {
        this.purchaseValue = purchaseValue;
    }

    public float getSellValue() {
        return sellValue;
    }

    public void setSellValue(float sellValue) {
        this.sellValue = sellValue;
    }

    public float getAverageValue() {
        return averageValue;
    }

    public void setAverageValue(float averageValue) {
        this.averageValue = averageValue;
    }
}
