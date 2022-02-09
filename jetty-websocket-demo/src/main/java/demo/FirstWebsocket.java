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
import java.util.concurrent.Future;

@ServerEndpoint("/fw")
public class FirstWebsocket {
    static final Logger LOGGER = LoggerFactory.getLogger(FirstWebsocket.class);

    @OnOpen
    public void onOpen(Session session) throws IOException {
        try {
            Thread.currentThread().sleep(1000);
            Future<Void> deliveryProgress = session.getAsyncRemote().sendText("Hello User! I will send you 5 packages.");
            boolean delivered = deliveryProgress.isDone();
            LOGGER.info("The message is delivered : " + delivered);
            for (int i = 1; i < 6; i++) {
                Thread.currentThread().sleep(1000);
                session.getAsyncRemote().sendText(String.valueOf(i));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
