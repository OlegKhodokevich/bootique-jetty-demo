package io.bootique.jetty.demo;

import io.bootique.BQCoreModule;
import io.bootique.BQRuntime;
import io.bootique.Bootique;
import io.bootique.jetty.JettyModule;
import io.bootique.jetty.junit5.JettyTester;
import io.bootique.junit5.BQApp;
import io.bootique.junit5.BQTest;
import io.bootique.junit5.BQTestFactory;
import io.bootique.junit5.BQTestScope;
import io.bootique.junit5.BQTestTool;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@BQTest
public abstract class JettyBaseTest {

    protected static final String OUT_CONTENT = "____content_stream____";

    @BQTestTool(BQTestScope.GLOBAL)
    static final BQTestFactory testFactory = new BQTestFactory();

    @BQTestTool(BQTestScope.GLOBAL)
    static final io.bootique.jetty.junit5.JettyTester jetty = JettyTester.create();


    @BQApp(value = BQTestScope.GLOBAL)
    static final BQRuntime app = Bootique.app("-s")
            .autoLoadModules()
            .module(b -> BQCoreModule.extend(b).setProperty("bq.jetty.context", "myapp"))
            .module(b -> JettyModule.extend(b).addServlet(ContentServlet.class))
            .module(jetty.moduleReplacingConnectors())
            // for predictable URL assertions
            .module(b -> BQCoreModule.extend(b).setProperty("bq.jetty.connectors[0].host", "127.0.0.1"))
            .createRuntime();


    @WebServlet(urlPatterns = "/*")
    static class ContentServlet extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
            String statusString = req.getParameter("wantedStatus");
            int status = statusString != null ? Integer.parseInt(statusString) : 200;

            if(status == 200) {

                String contentType = req.getParameter("wantedType");
                if(contentType != null) {
                    resp.setContentType(contentType);
                }

                resp.getWriter().append(OUT_CONTENT);
            }
            else {
                resp.setStatus(status);
            }
        }
    }
}
