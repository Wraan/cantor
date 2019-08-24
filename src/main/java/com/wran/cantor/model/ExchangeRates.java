package com.wran.cantor.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "exchange_rates")
public class ExchangeRates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "publication_date")
    private Date publicationDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usd_id", referencedColumnName = "id")
    private CurrencyRates usd;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "eur_id", referencedColumnName = "id")
    private CurrencyRates eur;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "chf_id", referencedColumnName = "id")
    private CurrencyRates chf;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rub_id", referencedColumnName = "id")
    private CurrencyRates rub;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "czk_id", referencedColumnName = "id")
    private CurrencyRates czk;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "gbp_id", referencedColumnName = "id")
    private CurrencyRates gbp;

    public ExchangeRates() {
    }

    public ExchangeRates(Date publicationDate, CurrencyRates usd, CurrencyRates eur, CurrencyRates chf, CurrencyRates rub,
                         CurrencyRates czk, CurrencyRates gbp) {
        this.publicationDate = publicationDate;
        this.usd = usd;
        this.eur = eur;
        this.chf = chf;
        this.rub = rub;
        this.czk = czk;
        this.gbp = gbp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public CurrencyRates getUsd() {
        return usd;
    }

    public void setUsd(CurrencyRates usd) {
        this.usd = usd;
    }

    public CurrencyRates getEur() {
        return eur;
    }

    public void setEur(CurrencyRates eur) {
        this.eur = eur;
    }

    public CurrencyRates getChf() {
        return chf;
    }

    public void setChf(CurrencyRates chf) {
        this.chf = chf;
    }

    public CurrencyRates getRub() {
        return rub;
    }

    public void setRub(CurrencyRates rub) {
        this.rub = rub;
    }

    public CurrencyRates getCzk() {
        return czk;
    }

    public void setCzk(CurrencyRates czk) {
        this.czk = czk;
    }

    public CurrencyRates getGbp() {
        return gbp;
    }

    public void setGbp(CurrencyRates gbp) {
        this.gbp = gbp;
    }
}
