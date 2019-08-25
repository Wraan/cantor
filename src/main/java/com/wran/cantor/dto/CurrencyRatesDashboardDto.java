package com.wran.cantor.dto;

import java.io.Serializable;

public class CurrencyRatesDashboardDto implements Serializable {

    private String code;
    private int unit;
    private double purchaseValue;
    private double sellValue;

    public CurrencyRatesDashboardDto() {
    }

    public CurrencyRatesDashboardDto(String code, int unit, double purchaseValue, double sellValue) {
        this.code = code;
        this.unit = unit;
        this.purchaseValue = purchaseValue;
        this.sellValue = sellValue;
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

    public void setPurchaseValue(double purcheaseValue) {
        this.purchaseValue = purcheaseValue;
    }

    public double getSellValue() {
        return sellValue;
    }

    public void setSellValue(double sellValue) {
        this.sellValue = sellValue;
    }
}
