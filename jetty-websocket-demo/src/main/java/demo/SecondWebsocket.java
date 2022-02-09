package demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint(value = "/sw")
public class SecondWebsocket {
    static final Logger LOGGER = LoggerFactory.getLogger(SecondWebsocket.class);


    @OnOpen
    public void onOpen(Session session) throws IOException {
    }

    @OnMessage
    public void onMessage(Session session, String  message) throws IOException {
        // Handle new messages
    }

    @OnClose
    public void onClose(Session session) throws IOException {
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // Do error handling here
    }
}
