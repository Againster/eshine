package controller;

import bean.WSChat;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;

/**
 * Created by root on 6/13/17.
 */
public class WebsocketChat implements WebSocketHandler{

    Logger log = LoggerFactory.getLogger("login-error");
    ObjectMapper mapper = new ObjectMapper();
    HashSet<WebSocketSession> wset;

    WebsocketChat() {
        wset = new HashSet<WebSocketSession>();
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        log.info("websocket established");
        wset.add(webSocketSession);

        WSChat c = new WSChat();
        c.setId(webSocketSession.getId());
        c.setType("identity");
        c.setDate((new Date().toString()));
        String x = wrapMessage(c);
        log.info(x);

        TextMessage m = new TextMessage(x);
        webSocketSession.sendMessage(m);
    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        WSChat c = new WSChat();
        c.setId(webSocketSession.getId());
        c.setType("chat");
        c.setDate((new Date()).toString());
        c.setMsg(webSocketMessage.getPayload().toString());
        String x = wrapMessage(c);
        log.info(x);
        for(WebSocketSession ws: wset) {
            ws.sendMessage(new TextMessage(x));
        }
    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        log.error("websocket error: {}", throwable.getMessage());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        log.info("websocket connection closed");
        wset.remove(webSocketSession);
    }

    @Override
    public boolean supportsPartialMessages() {
        return true;
    }

    private String wrapMessage (WSChat c) throws IOException {
        return mapper.writeValueAsString(c);
    }
}
