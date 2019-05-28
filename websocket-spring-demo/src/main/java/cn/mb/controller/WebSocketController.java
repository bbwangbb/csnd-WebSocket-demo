package cn.mb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/testWebSocket")
    @SendTo("/topic/destination")
    public String testWebSocket(String message) throws Exception {
        /**
         * 单播（私聊）：
         *      1.两人订阅相同的 url
         *      2.这样发信息的时候可以互相看到，数据库中可以在存储好友的信息的时候带上一个url（个人想法）
         */
        simpMessagingTemplate.convertAndSend("/topic/bb", "我是bb，你在吗？");
        System.out.println("客户端说：" + message);
        return "Hi, client!";
    }
}