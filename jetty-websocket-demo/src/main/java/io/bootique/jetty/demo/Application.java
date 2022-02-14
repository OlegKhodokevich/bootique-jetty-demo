package io.bootique.jetty.demo;

import io.bootique.BaseModule;
import io.bootique.Bootique;
import io.bootique.di.Binder;
import io.bootique.jetty.JettyModule;
import io.bootique.jetty.websocket.JettyWebSocketModule;

public class Application extends BaseModule {

    public static void main(String[] args) {
        Bootique.app(args)
                .autoLoadModules()
                .module(Application.class)
                .exec()
                .exit();
    }

    @Override
    public void configure(Binder binder) {

        // FirstWebsocket is a singleton endpoint
        binder.bind(FirstWebsocket.class).inSingletonScope();

        // SecondWebsocket is an instance-per-peer endpoint
        binder.bind(SecondWebsocket.class);
//        JettyModule.extend(binder).useDefaultServlet();
        JettyModule.extend(binder).addStaticServlet("s1", "/*");
        // both can be registered to handle WebSocket connections
        JettyWebSocketModule.extend(binder)
                .addEndpoint(FirstWebsocket.class)
                .addEndpoint(SecondWebsocket.class);
    }
}
