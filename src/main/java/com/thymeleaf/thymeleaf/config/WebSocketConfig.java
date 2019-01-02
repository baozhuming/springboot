package com.thymeleaf.thymeleaf.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * WebSocket配置：1
 */
@Component
@Configuration
@EnableWebSocketMessageBroker//通过注解开启使用STOMP协议来传输基于代理（message broker）的消息，这时控制器支持使用@MessageMapping，就像@RequestMapping
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {//注册STOMP协议的节点（endpoint），并映射的指定的url
        registry.addEndpoint("/endpointWisely").withSockJS();//注册一个STOPMP的endpoint，并指定使用SockJS协议
        registry.addEndpoint("/endpointChat").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {//配置消息代理（Message Broker）
        registry.enableSimpleBroker("/queue","/topic");//广播式配置一个/topic消息代理
        //点对点式应增加一个/queue消息代理
    }
}
