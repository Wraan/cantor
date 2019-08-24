package com.wran.cantor.dto;

public class ExchangeRatesDashboardDto {

    private CurrencyRatesDashboardDto USD;
    private CurrencyRatesDashboardDto EUR;
    private CurrencyRatesDashboardDto CHF;
    private CurrencyRatesDashboardDto RUB;
    private CurrencyRatesDashboardDto CZK;
    private CurrencyRatesDashboardDto GBP;

    public ExchangeRatesDashboardDto(CurrencyRatesDashboardDto USD, CurrencyRatesDashboardDto EUR, CurrencyRatesDashboardDto CHF, CurrencyRatesDashboardDto RUB, CurrencyRatesDashboardDto CZK, CurrencyRatesDashboardDto GBP) {
        this.USD = USD;
        this.EUR = EUR;
        this.CHF = CHF;
        this.RUB = RUB;
        this.CZK = CZK;
        this.GBP = GBP;
    }

    public CurrencyRatesDashboardDto getUSD() {
        return USD;
    }

    public void setUSD(CurrencyRatesDashboardDto USD) {
        this.USD = USD;
    }

    public CurrencyRatesDashboardDto getEUR() {
        return EUR;
    }

    public void setEUR(CurrencyRatesDashboardDto EUR) {
        this.EUR = EUR;
    }

    public CurrencyRatesDashboardDto getCHF() {
        return CHF;
    }

    public void setCHF(CurrencyRatesDashboardDto CHF) {
        this.CHF = CHF;
    }

    public CurrencyRatesDashboardDto getRUB() {
        return RUB;
    }

    public void setRUB(CurrencyRatesDashboardDto RUB) {
        this.RUB = RUB;
    }

    public CurrencyRatesDashboardDto getCZK() {
        return CZK;
    }

    public void setCZK(CurrencyRatesDashboardDto CZK) {
        this.CZK = CZK;
    }

    public CurrencyRatesDashboardDto getGBP() {
        return GBP;
    }

    public void setGBP(CurrencyRatesDashboardDto GBP) {
        this.GBP = GBP;
    }
}
