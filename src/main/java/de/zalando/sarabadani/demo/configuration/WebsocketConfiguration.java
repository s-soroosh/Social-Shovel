package de.zalando.sarabadani.demo.configuration;

import de.zalando.sarabadani.demo.websocket.SimpleHandler;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * Created by SOROOSH on 4/4/15.
 */
@Configuration
@EnableAutoConfiguration
@EnableWebSocket
public class WebsocketConfiguration implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(simpleHandler(),"/socket/simple").withSockJS();

    }

    public WebSocketHandler simpleHandler(){
        return new SimpleHandler();
    }
}
