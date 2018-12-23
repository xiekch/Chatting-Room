package xiekch.chattingroom.service;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import java.util.List;


@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer{//implements  WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/websocket").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        System.out.println("start websocket");
        config.enableSimpleBroker("/userChat","/rooms");
        config.setApplicationDestinationPrefixes("/app");
    }

    // @Override
    // public void configureClientInboundChannel(ChannelRegistration channelRegistration) {
    // }

    // @Override
    // public void configureClientOutboundChannel(ChannelRegistration channelRegistration) {
    // }

    // @Override
    // public boolean configureMessageConverters(List<MessageConverter> messageConverters){
    //     return true;
    // }
}
