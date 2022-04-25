package io.quarkus.keycloak.pep.test;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import io.quarkus.security.Authenticated;

@Path("/api2/resource")
@Authenticated
public class ProtectedResource2 {

    @GET
    public String testResource() {
        // This method must not be invoked
        throw new RuntimeException();
    }
}
