package com.wran.cantor.dto;

import java.io.Serializable;

public class CurrencyRatesDashboardDto implements Serializable {

    private String code;
    private int unit;
    private float purchaseValue;
    private float sellValue;

    public CurrencyRatesDashboardDto() {
    }

    public CurrencyRatesDashboardDto(String code, int unit, float purchaseValue, float sellValue) {
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

    public float getPurchaseValue() {
        return purchaseValue;
    }

    public void setPurchaseValue(float purcheaseValue) {
        this.purchaseValue = purcheaseValue;
    }

    public float getSellValue() {
        return sellValue;
    }

    public void setSellValue(float sellValue) {
        this.sellValue = sellValue;
    }
}
