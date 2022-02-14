package io.bootique.jetty.demo;

import io.bootique.BQRuntime;
import io.bootique.jetty.server.ServerHolder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JettyConfigTest extends JettyBaseTest {

    @Test
    public void testWithBQTestFactory() {
        io.bootique.jetty.junit5.JettyTester tester = io.bootique.jetty.junit5.JettyTester.create();
        BQRuntime runtime = testFactory.app("--server").autoLoadModules().module(tester.moduleReplacingConnectors()).createRuntime();
        runtime.run();
        assertEquals(tester.getPort(), runtime.getInstance(ServerHolder.class).getConnector().getPort());
    }

    @Test
    public void testGetServerUrl() {
        String url = jetty.getUrl();
        Assertions.assertNotNull(url);
        assertEquals("http://127.0.0.1:" + jetty.getPort() + "/myapp", url);
    }

    @Test
    public void testGetClient() {
        WebTarget client = jetty.getTarget();
        Assertions.assertNotNull(client);

        assertEquals("http://127.0.0.1:" + jetty.getPort() + "/myapp", client.getUri().toString());

        Response r = client.request().get();
        assertEquals(Response.Status.OK.getStatusCode(), r.getStatus());
        assertEquals(OUT_CONTENT, r.readEntity(String.class));
    }
}
