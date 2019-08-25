package com.wran.cantor.service;

import com.wran.cantor.config.CurrenciesHistoryStorage;
import com.wran.cantor.dto.*;
import com.wran.cantor.model.CurrencyRates;
import com.wran.cantor.model.ExchangeRates;
import com.wran.cantor.repository.ExchangeRatesRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.DateUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class CurrenciesService {

    private Logger LOGGER = LogManager.getLogger(getClass());

    @Autowired
    private CurrenciesHistoryStorage historyStorage;

    @Autowired
    private ExchangeRatesRepository exchangeRatesRepository;

    @Autowired
    private DtoConverterService converterService;


    public ExchangeRates save(ExchangeRatesWebsocketDto currencies) {
        ExchangeRates rates = converterService.convertFromExchangeRatesDto(currencies);

        Calendar cal = Calendar.getInstance();
        cal.setTime(rates.getPublicationDate());
        cal.add(Calendar.HOUR_OF_DAY, 2);
        rates.setPublicationDate(cal.getTime());

        return exchangeRatesRepository.save(rates);
    }
    public ExchangeRates save(ExchangeRates exchangeRates){
        return exchangeRatesRepository.save(exchangeRates);
    }

    public ExchangeRates getNewestExchangeRates(){
        return exchangeRatesRepository.findTopByOrderByIdDesc();
    }


    public List<ExchangeRates> getLatest20ExchangeRates() {
        return exchangeRatesRepository.findTop20ByOrderByIdDesc();
    }
}
