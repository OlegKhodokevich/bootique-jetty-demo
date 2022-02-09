package demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "servlet", urlPatterns = "/start")
public class AnnotatedServlet extends HttpServlet {

    static final Logger LOGGER = LoggerFactory.getLogger(AnnotatedServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOGGER.info("GET servlet");

        resp.setContentType("text/plain");
        PrintWriter pw = resp.getWriter();
        pw.println("This is servlet.");
        pw.close();
    }
}
