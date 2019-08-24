package com.wran.cantor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Currencies implements Serializable {

    @JsonProperty("PublicationDate")
    private Date publicationDate;
    @JsonProperty("Items")
    private List<Currency> items;

    public Currencies() {
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public List<Currency> getItems() {
        return items;
    }

    public void setItems(List<Currency> items) {
        this.items = items;
    }
}
