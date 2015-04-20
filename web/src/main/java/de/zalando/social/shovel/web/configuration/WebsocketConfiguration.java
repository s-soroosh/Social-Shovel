package de.zalando.social.shovel.web.configuration;

import de.zalando.social.shovel.web.websocket.SimpleHandler;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
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

    @Bean
    public WebSocketHandler simpleHandler(){
        return new SimpleHandler();
    }
}
