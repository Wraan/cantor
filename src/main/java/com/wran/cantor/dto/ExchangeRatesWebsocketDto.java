package com.wran.cantor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ExchangeRatesWebsocketDto implements Serializable {

    @JsonProperty("PublicationDate")
    private Date publicationDate;
    @JsonProperty("Items")
    private List<CurrencyRatesWebsocketDto> items;

    public ExchangeRatesWebsocketDto() {
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public List<CurrencyRatesWebsocketDto> getItems() {
        return items;
    }

    public void setItems(List<CurrencyRatesWebsocketDto> items) {
        this.items = items;
    }
}
