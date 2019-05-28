package cn.mb.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.web.socket.messaging.SessionConnectEvent;

public class STOMPConnectEventListener implements ApplicationListener<SessionConnectEvent> {


    @Override
    public void onApplicationEvent(SessionConnectEvent sessionConnectEvent) {

        //打开链接时会触发此方法

    }


}
