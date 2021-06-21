package io.quarkus.couchbase.client;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

import com.couchbase.client.java.Cluster;

@ApplicationScoped
public class CouchbaseProducer {

    private volatile Cluster cluster;

    void initialize(Cluster cluster) {
        this.cluster = cluster;
    }

    @Singleton
    @Produces
    public Cluster cluster() {
        return cluster;
    }
}
