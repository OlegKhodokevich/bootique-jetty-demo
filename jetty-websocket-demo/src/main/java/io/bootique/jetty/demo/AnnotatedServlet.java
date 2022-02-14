package io.bootique.jetty.demo;

import io.bootique.resource.ResourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;

@WebServlet(name = "servlet", urlPatterns = "")
public class AnnotatedServlet extends HttpServlet {
//
//    static final Logger LOGGER = LoggerFactory.getLogger(AnnotatedServlet.class);
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        LOGGER.info("GET servlet");
//
////        File htmlFile = new File("classpath:io/bootique/jetty/demo/index.html");
////        Desktop.getDesktop().browse(htmlFile.toURI());
//
//        resp.sendRedirect("index.html");
//    }
}
