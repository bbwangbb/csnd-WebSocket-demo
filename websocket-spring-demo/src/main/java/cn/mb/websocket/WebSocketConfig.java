package cn.mb.websocket;

import cn.mb.interceptor.HttpHandShakeInterceptor;
import cn.mb.listener.STOMPConnectEventListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker//启用websocket注解
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override//配置消息代理
    public void configureMessageBroker(MessageBrokerRegistry config) {
        /**
         * 与 @MessageMapping 中的url拼接后就是 客户端可以发送数据的 url
         *      @MessageMapping("/testWebSocket")
         *      客户端即可向 /app/testWebSocket 发送数据
         */
        config.setApplicationDestinationPrefixes("/app");
        //前端可以订阅 /topic/... 的url
        config.enableSimpleBroker("/topic");
    }

    @Override//注册 Stomp 端点
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //添加一个/spring-websocket端点，客户端就可以通过这个端点来进行连接；
        registry.addEndpoint("/spring-websocket")
                .addInterceptors(new HttpHandShakeInterceptor())//设置握手拦截器
                .setAllowedOrigins("*") // 添加允许跨域访问
                .withSockJS();//添加SockJS支持
    }

    @Bean//注入监听器
    public STOMPConnectEventListener STOMPConnectEventListener(){
        return new STOMPConnectEventListener();
    }

}