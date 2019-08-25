package com.wran.cantor.dto;

import java.io.Serializable;
import java.util.Date;

public class ExchangeRatesDashboardDto implements Serializable {

    private Date publicationDate;
    private CurrencyRatesDashboardDto usd;
    private CurrencyRatesDashboardDto eur;
    private CurrencyRatesDashboardDto chf;
    private CurrencyRatesDashboardDto rub;
    private CurrencyRatesDashboardDto czk;
    private CurrencyRatesDashboardDto gbp;

    public ExchangeRatesDashboardDto() {
    }

    public ExchangeRatesDashboardDto(Date publicationDate, CurrencyRatesDashboardDto usd, CurrencyRatesDashboardDto eur,
                                     CurrencyRatesDashboardDto chf, CurrencyRatesDashboardDto rub, CurrencyRatesDashboardDto czk, CurrencyRatesDashboardDto gbp) {
        this.publicationDate = publicationDate;
        this.usd = usd;
        this.eur = eur;
        this.chf = chf;
        this.rub = rub;
        this.czk = czk;
        this.gbp = gbp;
    }

    public CurrencyRatesDashboardDto getUsd() {
        return usd;
    }

    public void setUsd(CurrencyRatesDashboardDto usd) {
        this.usd = usd;
    }

    public CurrencyRatesDashboardDto getEur() {
        return eur;
    }

    public void setEur(CurrencyRatesDashboardDto eur) {
        this.eur = eur;
    }

    public CurrencyRatesDashboardDto getChf() {
        return chf;
    }

    public void setChf(CurrencyRatesDashboardDto chf) {
        this.chf = chf;
    }

    public CurrencyRatesDashboardDto getRub() {
        return rub;
    }

    public void setRub(CurrencyRatesDashboardDto rub) {
        this.rub = rub;
    }

    public CurrencyRatesDashboardDto getCzk() {
        return czk;
    }

    public void setCzk(CurrencyRatesDashboardDto czk) {
        this.czk = czk;
    }

    public CurrencyRatesDashboardDto getGbp() {
        return gbp;
    }

    public void setGbp(CurrencyRatesDashboardDto gbp) {
        this.gbp = gbp;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }
}
