package com.wran.cantor.config;

import com.wran.cantor.dto.ExchangeRatesWebsocketDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CurrenciesHistoryStorage {

    private List<ExchangeRatesWebsocketDto> list = new ArrayList<>();

    private Logger LOGGER = LogManager.getLogger(getClass());

    @Value("${history.storage.max}")
    private int historyStorageMaxSize;

    public void addToList(ExchangeRatesWebsocketDto currencies){
        list.add(0, currencies);
        if(list.size() > historyStorageMaxSize)
            list.remove(list.size() - 1);

        LOGGER.info("Added to history storage, list size: {}", list.size());
    }
}
