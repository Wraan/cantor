package com.wran.cantor.config.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

@Configuration
public class SocketConnector {

    @Lazy
    @Autowired
    private WSHandler wsHandler;

    private WebSocketConnectionManager manager;

    private boolean connected;

    public void reconnect(){
        manager.stop();
        manager.start();
    }

    @Bean
    public WebSocketConnectionManager wsConnectionManager() {

        manager = new WebSocketConnectionManager(client(), wsHandler,
                "ws://webtask.future-processing.com:8068/ws/currencies?format=json");

        manager.setAutoStartup(true);

        return manager;
    }

    @Bean
    public StandardWebSocketClient client() {
        return new StandardWebSocketClient();
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }
}
