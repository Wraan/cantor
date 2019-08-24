package com.wran.cantor.config.websocket;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wran.cantor.config.CurrenciesHistoryStorage;
import com.wran.cantor.dto.Currencies;
import com.wran.cantor.model.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

@Component
public class WSHandler implements WebSocketHandler {

    private Logger LOGGER = LogManager.getLogger(getClass());

    ObjectMapper objectMapper = new ObjectMapper();

    @Lazy
    @Autowired
    private SimpMessagingTemplate template;

    @Lazy
    @Autowired
    private CurrenciesHistoryStorage currenciesHistoryStorage;

    @Lazy @Autowired
    private SocketConnector socketConnector;

    public WSHandler() {
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        LOGGER.info("Successfully connected to server!");
    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> message) throws Exception {
        Currencies currencies = null;
        try{
            currencies = objectMapper.readValue(message.getPayload().toString(), Currencies.class);
        }
        catch (JsonParseException e){
        }

        if(currencies != null){
            currenciesHistoryStorage.addToList(currencies);
            Message out = new Message("Working!", "Server");
            template.convertAndSend("/topic/greetings", out);
        }
    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        LOGGER.info("Connection error");

    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        LOGGER.info("Connection closed. Trying to reconnect...");
        socketConnector.reconnect();

    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}