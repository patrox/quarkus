package io.quarkus.keycloak.pep.test;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/api/public")
public class PublicResource {

    @GET
    public void serve() {
        // no-op
    }
}
