package com.cygao.config;

import com.cygao.interceptor.HandShakeInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @author cygao
 * {@code @EnableWebSocketMessageBroker}注释能启用WebSocket进行消息代理
 */
@EnableWebSocketMessageBroker
@Configuration
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

  /**
   * 将"/websocket"注册为STOMP端点，并添加和启用SockJS回退选项
   * 使该URL可供WebSocket或SockJS客户端访问
   */
  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    registry.addEndpoint("/websocket").addInterceptors(new HandShakeInterceptor()).withSockJS();
  }

  /**
   * 启用一个简单的基于内存的消息代理，将信息传回以"/topic"为前缀的目的客户端
   * 它还为绑定到用{@code @MessageMapping}注释的方法的消息指定"/app"为前缀
   */
  @Override
  public void configureMessageBroker(MessageBrokerRegistry registry) {
    registry.enableSimpleBroker("/topic");
    registry.setApplicationDestinationPrefixes("/app");
  }
}
