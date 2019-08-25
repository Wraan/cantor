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
    private double purchaseValue;
    @Column(name = "sell_value")
    private double sellValue;
    @Column(name = "average_value")
    private double averageValue;

    public CurrencyRates() {
    }

    public CurrencyRates(String code, int unit, double purchaseValue, double sellValue, double averageValue) {
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

    public double getPurchaseValue() {
        return purchaseValue;
    }

    public void setPurchaseValue(double purchaseValue) {
        this.purchaseValue = purchaseValue;
    }

    public double getSellValue() {
        return sellValue;
    }

    public void setSellValue(double sellValue) {
        this.sellValue = sellValue;
    }

    public double getAverageValue() {
        return averageValue;
    }

    public void setAverageValue(double averageValue) {
        this.averageValue = averageValue;
    }
}
