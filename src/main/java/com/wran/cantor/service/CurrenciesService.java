package com.wran.cantor.service;

import com.wran.cantor.config.CurrenciesHistoryStorage;
import com.wran.cantor.dto.ExchangeRatesWebsocketDto;
import com.wran.cantor.dto.CurrencyRatesWebsocketDto;
import com.wran.cantor.model.CurrencyRates;
import com.wran.cantor.model.ExchangeRates;
import com.wran.cantor.repository.ExchangeRatesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrenciesService {

    @Autowired
    private CurrenciesHistoryStorage historyStorage;

    @Autowired
    private ExchangeRatesRepository exchangeRatesRepository;


    public ExchangeRates save(ExchangeRatesWebsocketDto currencies) {
        ExchangeRates rates = convertFromExchangeRatesDto(currencies);
        return exchangeRatesRepository.save(rates);
    }
    public ExchangeRates save(ExchangeRates exchangeRates){
        return exchangeRatesRepository.save(exchangeRates);
    }

    private ExchangeRates convertFromExchangeRatesDto(ExchangeRatesWebsocketDto currencies){
        ExchangeRates rates = new ExchangeRates();
        rates.setPublicationDate(currencies.getPublicationDate());

        currencies.getItems().forEach(currency ->{
            if(currency.getCode().equals("USD")) rates.setUsd(convertFromCurrencyRatesDto(currency));
            if(currency.getCode().equals("EUR")) rates.setEur(convertFromCurrencyRatesDto(currency));
            if(currency.getCode().equals("CHF")) rates.setChf(convertFromCurrencyRatesDto(currency));
            if(currency.getCode().equals("RUB")) rates.setRub(convertFromCurrencyRatesDto(currency));
            if(currency.getCode().equals("CZK")) rates.setCzk(convertFromCurrencyRatesDto(currency));
            if(currency.getCode().equals("GBP")) rates.setGbp(convertFromCurrencyRatesDto(currency));
        });

        return rates;
    }

    private CurrencyRates convertFromCurrencyRatesDto(CurrencyRatesWebsocketDto currency){
        return new CurrencyRates(currency.getCode(), currency.getUnit(), currency.getPurchasePrice(),
                currency.getSellPrice(), currency.getAveragePrice());
    }
}
