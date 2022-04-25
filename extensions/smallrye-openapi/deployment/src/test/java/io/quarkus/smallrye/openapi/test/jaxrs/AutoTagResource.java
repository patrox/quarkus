package io.quarkus.smallrye.openapi.test.jaxrs;

import jakarta.ws.rs.Path;

@Path("/address")
public class AutoTagResource implements AbstractAutoTagResource<String> {
    @Override
    public String getById(long id) {
        return "Disney Land, Gate " + id;
    }
}
