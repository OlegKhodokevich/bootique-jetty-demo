package io.bootique.jetty.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "servlet1", urlPatterns = "/s1")
public class AnnotatedServlet1 extends HttpServlet {

    static final Logger LOGGER = LoggerFactory.getLogger(AnnotatedServlet1.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOGGER.info("GET servlet1");
        
        resp.setContentType("text/plain");
        PrintWriter pw = resp.getWriter();
        pw.println("Servlet 1!");
        pw.close();
    }
}
