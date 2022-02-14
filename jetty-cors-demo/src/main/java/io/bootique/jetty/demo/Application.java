package io.bootique.jetty.demo;

import io.bootique.BaseModule;
import io.bootique.Bootique;
import io.bootique.di.Binder;
import io.bootique.jetty.JettyModule;
import io.bootique.jetty.cors.JettyCorsModule;
import io.bootique.jetty.cors.JettyCorsModuleProvider;

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


        // register servlets and filters
        JettyModule.extend(binder)
                // annotated servlets can be added directly
                .addStaticServlet("first","/*")
                .addStaticServlet("allowed","/allowed/*")
                .addStaticServlet("forbidden","/forbidden/*");

    }
}
