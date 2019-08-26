package com.wran.cantor.service;

import com.wran.cantor.dto.*;
import com.wran.cantor.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class DtoConverterService {

    @Autowired
    private CurrenciesService currenciesService;

    public ExchangeRates convertFromExchangeRatesDto(ExchangeRatesWebsocketDto currencies){
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

    public CurrencyRates convertFromCurrencyRatesDto(CurrencyRatesWebsocketDto currency){
        return new CurrencyRates(currency.getCode(), currency.getUnit(), currency.getPurchasePrice(),
                currency.getSellPrice(), currency.getAveragePrice());
    }



    public ExchangeRatesDashboardDto convertToDashboardDto(ExchangeRates rates) {
        if (rates == null) return null;

        return new ExchangeRatesDashboardDto(rates.getPublicationDate(), convertToDashboardDto(rates.getUsd()),
                convertToDashboardDto(rates.getEur()), convertToDashboardDto(rates.getChf()),
                convertToDashboardDto(rates.getRub()), convertToDashboardDto(rates.getCzk()),
                convertToDashboardDto(rates.getGbp()));
    }

    public CurrencyRatesDashboardDto convertToDashboardDto(CurrencyRates rates) {
        return new CurrencyRatesDashboardDto(rates.getCode(), rates.getUnit(),
                rates.getPurchaseValue(), rates.getSellValue());
    }

    public WalletDashboardDto convertToDashboardDto(Wallet wallet) {
        return new WalletDashboardDto(wallet.getFunds(), wallet.getUsdAmount(), wallet.getEurAmount(),
                wallet.getChfAmount(), wallet.getRubAmount(), wallet.getCzkAmount(), wallet.getGbpAmount());
    }

    public List<ExchangeRatesDashboardDto> convertToDashboardDto(List<ExchangeRates> latestRates) {
        List<ExchangeRatesDashboardDto> outputRates = new ArrayList<>();
        for (ExchangeRates latestRate : latestRates) {
            outputRates.add(convertToDashboardDto(latestRate));
        }
        return outputRates;
    }

    public Transaction convertFromDashboardDto(TransactionDashboardDto transactionDto) {
        ExchangeRates exchangeRates = currenciesService.getNewestExchangeRates();
        CurrencyRates currencyRates;
        switch (transactionDto.getCode()){
            case "USD": currencyRates = exchangeRates.getUsd(); break;
            case "EUR": currencyRates = exchangeRates.getEur(); break;
            case "CHF": currencyRates = exchangeRates.getChf(); break;
            case "RUB": currencyRates = exchangeRates.getRub(); break;
            case "CZK": currencyRates = exchangeRates.getCzk(); break;
            case "GBP": currencyRates = exchangeRates.getGbp(); break;
            default: currencyRates = new CurrencyRates();
        }
        double rate = transactionDto.getAction().equals("BUY") ? currencyRates.getSellValue() : currencyRates.getPurchaseValue();

        return new Transaction(transactionDto.getAction(), transactionDto.getCode(),
                transactionDto.getAmount(), currencyRates.getUnit(), rate, new Date(System.currentTimeMillis()));
    }
}
