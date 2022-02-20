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

@ServerEndpoint("/fw")
public class FirstWebsocket {
    static final Logger LOGGER = LoggerFactory.getLogger(FirstWebsocket.class);

    @OnOpen
    public void onOpen(Session session) throws IOException {
        String message = "FirstWebsocket : Hello User!";
        Future<Void> deliveryProgress = session.getAsyncRemote().sendText(message);
        LOGGER.info("The message onOpen '" + message + "' was delivered : " + deliveryProgress.isDone());
    }

    @OnMessage
    public void onMessage(Session session, String  message) throws IOException {
        String answer = "FirstWebsocket : answer on message'" + message + "'";
        Future<Void> deliveryProgress = session.getAsyncRemote().sendText(answer);
        LOGGER.info("The message onMessage '" + answer + "'  was delivered : " + deliveryProgress.isDone());
    }

    @OnClose
    public void onClose(Session session) throws IOException {
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // Do error handling here
    }
}
