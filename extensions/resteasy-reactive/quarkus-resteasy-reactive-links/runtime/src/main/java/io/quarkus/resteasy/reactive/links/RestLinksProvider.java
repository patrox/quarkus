package io.quarkus.resteasy.reactive.links;

import java.util.Collection;

import jakarta.ws.rs.core.Link;

public interface RestLinksProvider {

    Collection<Link> getTypeLinks(Class<?> elementType);

    <T> Collection<Link> getInstanceLinks(T instance);
}
