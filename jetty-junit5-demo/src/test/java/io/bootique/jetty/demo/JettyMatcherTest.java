package io.bootique.jetty.demo;

import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static io.bootique.jetty.junit5.JettyTester.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JettyMatcherTest extends JettyBaseTest{

    @Test
    public void testMatchStatus200() {
        Response response1 = jetty.getTarget().request().get();
        matcher(response1).assertOk().assertContent(OUT_CONTENT);

        Response response2 = jetty.getTarget().request().get();
        assertOk(response2).assertContent(OUT_CONTENT);
    }

    @Test
    public void testMatchStatus200_ContentType() {
        Response r1 = jetty.getTarget().queryParam("wantedType", "application/json").request().get();
        matcher(r1).assertOk().assertContentType(MediaType.APPLICATION_JSON_TYPE);

        // non-standard type
        Response r2 = jetty.getTarget().queryParam("wantedType", "application/geo+json").request().get();
        matcher(r2).assertOk().assertContentType(new MediaType("application", "*"));

        // string comparision
        Response r3 = jetty.getTarget().queryParam("wantedType", "application/geo+json").request().get();
        matcher(r3).assertOk().assertContentType("application/*");
    }

    @Test
    public void testMatch200_CustomAssertions() {
        Response r = jetty.getTarget().request().get();
        matcher(r).assertOk().assertContent(c -> {
            assertNotNull(c);
            assertTrue(c.contains("_stream_"));
        });
    }


    @Test
    public void testMatch201() {
        Response r1 = jetty.getTarget().queryParam("wantedStatus", "201").request().get();
        matcher(r1).assertCreated();

        // try a different style
        Response r2 = jetty.getTarget().queryParam("wantedStatus", "201").request().get();
        assertCreated(r2);
    }

    @Test
    public void testMatch400() {
        Response r1 = jetty.getTarget().queryParam("wantedStatus", "400").request().get();
        matcher(r1).assertBadRequest();

        // try a different style
        Response r2 = jetty.getTarget().queryParam("wantedStatus", "400").request().get();
        assertBadRequest(r2);
    }

    @Test
    public void testMatch401() {
        Response r1 = jetty.getTarget().queryParam("wantedStatus", "401").request().get();
        matcher(r1).assertUnauthorized();

        // try a different style
        Response r2 = jetty.getTarget().queryParam("wantedStatus", "401").request().get();
        assertUnauthorized(r2);
    }

    @Test
    public void testMatch403() {
        Response r1 = jetty.getTarget().queryParam("wantedStatus", "403").request().get();
        matcher(r1).assertForbidden();

        // try a different style
        Response r2 = jetty.getTarget().queryParam("wantedStatus", "403").request().get();
        assertForbidden(r2);
    }

    @Test
    public void testMatch404() {
        Response r1 = jetty.getTarget().queryParam("wantedStatus", "404").request().get();
        matcher(r1).assertNotFound();

        // try a different style
        Response r2 = jetty.getTarget().queryParam("wantedStatus", "404").request().get();
        assertNotFound(r2);
    }

    @Test
    public void testMatch500() {
        Response r1 = jetty.getTarget().queryParam("wantedStatus", "500").request().get();
        matcher(r1).assertStatus(500);

        // try a different style
        Response r2 = jetty.getTarget().queryParam("wantedStatus", "500").request().get();
        assertStatus(r2, 500);
    }
}
