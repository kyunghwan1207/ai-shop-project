package com.shop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@EnableWebSocketMessageBroker
@Configuration
public class StomWebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private String[] ALLOWED_URL_LIST = {
            "http://127.0.0.1:8080", "http://127.0.0.1", "http://10.10.10.115", "http://101.101.210.36"
    };
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").setAllowedOrigins(ALLOWED_URL_LIST).withSockJS();
        // 서버에 "/ws"라는 이름으로 web socket을 하나 만들어서 서비스 할 것임

        // registry.addEndpoint("/chbot").setAllowedOrigins("http://127.0.0.1:8080", "http://127.0.0.1").withSockJS();
    }

    /* 어플리케이션 내부에서 사용할 path를 지정할 수 있음 */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // registry.enableSimpleBroker("/send","/broadcast");
        registry.enableSimpleBroker("/send");
    }
}
