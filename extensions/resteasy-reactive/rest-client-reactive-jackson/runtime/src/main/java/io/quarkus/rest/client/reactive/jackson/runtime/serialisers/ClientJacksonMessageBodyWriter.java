package io.quarkus.rest.client.reactive.jackson.runtime.serialisers;

import static org.jboss.resteasy.reactive.server.jackson.JacksonMessageBodyWriterUtil.createDefaultWriter;
import static org.jboss.resteasy.reactive.server.jackson.JacksonMessageBodyWriterUtil.doLegacyWrite;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.MessageBodyWriter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class ClientJacksonMessageBodyWriter implements MessageBodyWriter<Object> {

    protected final ObjectMapper originalMapper;
    protected final ObjectWriter defaultWriter;

    @Inject
    public ClientJacksonMessageBodyWriter(ObjectMapper mapper) {
        this.originalMapper = mapper;
        this.defaultWriter = createDefaultWriter(mapper);
    }

    @Override
    public boolean isWriteable(Class type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return true;
    }

    @Override
    public void writeTo(Object o, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
            MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
        doLegacyWrite(o, annotations, httpHeaders, entityStream, defaultWriter);
    }
}
