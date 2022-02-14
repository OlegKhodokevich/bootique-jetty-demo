package io.bootique.jetty.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.Future;

@ServerEndpoint(value = "/sw")
public class SecondWebsocket {
    static final Logger LOGGER = LoggerFactory.getLogger(SecondWebsocket.class);


    @OnOpen
    public void onOpen(Session session) throws IOException {
        String message = "SecondWebsocket : Hello User!";
        Future<Void> deliveryProgress = session.getAsyncRemote().sendText(message);
        boolean delivered = deliveryProgress.isDone();
        LOGGER.info("The message onOpen " + message + " was delivered : " + delivered);
    }

    @OnMessage
    public void onMessage(Session session, String  message) throws IOException {
        String answer = "SecondWebsocket : answer on message '" + message + "'";
        Future<Void> deliveryProgress = session.getAsyncRemote().sendText(answer);
        boolean delivered = deliveryProgress.isDone();
        LOGGER.info("The message onMessage " + answer + " was delivered : " + delivered);
    }

    @OnClose
    public void onClose(Session session) throws IOException {
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // Do error handling here
    }
}
