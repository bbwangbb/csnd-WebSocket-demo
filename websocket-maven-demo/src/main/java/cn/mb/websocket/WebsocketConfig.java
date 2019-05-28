package cn.mb.websocket;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

//该注解用来指定连接的 url，类似请求的url -> ws://主机:端口/websocket 即为连接地址
@ServerEndpoint("/websocket")
public class WebsocketConfig {
    //可以通过此来记录在线人数
    public static int onlineCount = 0;

    //可以用通过静态集合的方式存储session对象，在别处可以直接调用来逐个发送消息
    public static CopyOnWriteArraySet<Session> sessions = new CopyOnWriteArraySet<>();

    public WebsocketConfig() {
        System.out.println("init...");
    }

    @OnOpen//连接打开时调用
    public void open(Session session) {
        sessions.add(session);
        System.out.println("open...");
    }

    @OnClose//连接关闭时调用
    public void close(Session session) {
        sessions.remove(session);
        System.out.println("close...");
    }

    @OnMessage//客户端发送消息时调用
    public void send(String message, Session session) {
        System.out.println("客户端信息：" + message);
        System.out.println("send...");
        //给用户发送信息
    }

    @OnError//发生错误时调用
    public void error(Session session, Throwable error) {
        System.out.println("error...");
    }

}
