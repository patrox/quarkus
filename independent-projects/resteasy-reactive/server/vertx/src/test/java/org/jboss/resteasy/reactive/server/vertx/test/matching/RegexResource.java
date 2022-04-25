package org.jboss.resteasy.reactive.server.vertx.test.matching;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/regex")
public class RegexResource {

    @GET
    @Path("/{pin:[A-Z0-9]{4}}")
    public String hello(@PathParam("pin") String name) {
        return "pin " + name;
    }

}
