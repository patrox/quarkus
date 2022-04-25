package io.quarkus.resteasy.reactive.server.test.security;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import io.smallrye.common.annotation.Blocking;

/**
 * @author Michal Szynkiewicz, michal.l.szynkiewicz@gmail.com
 */
@Path("/roles-blocking")
@PermitAll
@Blocking
public class RolesAllowedBlockingResource {
    @GET
    @RolesAllowed({ "user", "admin" })
    public String defaultSecurity() {
        return "default";
    }

    @Path("/admin")
    @RolesAllowed("admin")
    @GET
    public String admin() {
        return "admin";
    }

}
