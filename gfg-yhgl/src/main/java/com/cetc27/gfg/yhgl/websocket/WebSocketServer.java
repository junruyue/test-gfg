package com.cetc27.gfg.yhgl.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint("/websocket/{sid}")
public class WebSocketServer {

    //当前连接数
    private static int onlineCount = 0;

    //客户端对用的WebSocket
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet =
            new CopyOnWriteArraySet<WebSocketServer>();

    //和客户端的Session
    private Session session;

    //接收的sid
    private String sid = "";

    //连接建立成功时调用
    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid) {
        this.session = session;
        webSocketSet.add(this);
        addOnlineCount();
        this.sid = sid;
        System.out.println("有新窗口开始监听:" + sid + ",当前在线人数为" + getOnlineCount());
        try {
            sendMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        subOnlineCount();
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount() + "退出登录");
    }

    //向客户端主动推送消息
    public void sendMessage() throws IOException {
        session.getBasicRemote().sendText("连接成功!");
    }


    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }

    public static CopyOnWriteArraySet<WebSocketServer> getWebSocketSet() {
        return webSocketSet;
    }

}
