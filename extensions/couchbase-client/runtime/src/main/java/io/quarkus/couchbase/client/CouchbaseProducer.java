package io.quarkus.couchbase.client;

import com.couchbase.client.java.Cluster;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

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
