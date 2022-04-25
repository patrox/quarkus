package io.quarkus.keycloak.pep.test;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import jakarta.inject.Inject;
import javax.security.auth.AuthPermission;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.ForbiddenException;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;

import org.keycloak.representations.idm.authorization.Permission;

import io.quarkus.security.identity.SecurityIdentity;
import io.smallrye.mutiny.Uni;
import io.vertx.core.http.HttpServerRequest;

@Path("/api/permission")
public class ProtectedResource {

    @Inject
    SecurityIdentity identity;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<List<Permission>> permissions() {
        return identity.checkPermission(new AuthPermission("Permission Resource"))
                .onItem().transform(new Function<Boolean, List<Permission>>() {
                    @Override
                    public List<Permission> apply(Boolean granted) {
                        if (granted) {
                            return identity.getAttribute("permissions");
                        }
                        throw new ForbiddenException();
                    }
                });
    }

    @Path("/claim-protected")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Permission> claimProtected() {
        return identity.getAttribute("permissions");
    }

    @Path("/http-response-claim-protected")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Permission> httpResponseClaimProtected() {
        return identity.getAttribute("permissions");
    }

    @Path("/body-claim")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Permission> bodyClaim(Map<String, Object> body, @Context HttpServerRequest request) {
        if (body == null || !body.containsKey("from-body")) {
            return Collections.emptyList();
        }
        return identity.getAttribute("permissions");
    }
}
