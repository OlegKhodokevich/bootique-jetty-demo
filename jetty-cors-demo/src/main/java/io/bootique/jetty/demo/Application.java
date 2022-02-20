package io.bootique.jetty.demo;

import io.bootique.BaseModule;
import io.bootique.Bootique;
import io.bootique.di.Binder;
import io.bootique.jetty.JettyModule;
import io.bootique.jetty.cors.JettyCorsModule;

public class Application extends BaseModule {


    public static void main(String[] args) {
        Bootique.app(args)
                .autoLoadModules()
                .module(Application.class)
                .module(JettyCorsModule.class)
                .exec()
                .exit();
    }

    @Override
    public void configure(Binder binder) {
        // register servlets
        JettyModule.extend(binder)
                .addStaticServlet("first","/*")
                .addStaticServlet("allowed","/allowed/*")
                .addStaticServlet("forbidden","/forbidden/*");
    }

}
