package io.quarkus.couchbase.client.health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Readiness;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;

@Readiness
@ApplicationScoped
public class CouchbaseHealthCheck implements HealthCheck {

    // TODO: use: https://docs.couchbase.com/java-sdk/current/concept-docs/health-check.html#ping as a basis

//    @Inject
//    @Identifier("default-kafka-broker")
//    Map<String, Object> config;

//    private AdminClient client;

    @PostConstruct
    void init() {
//        Map<String, Object> conf = new HashMap<>(config);
//        conf.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, "5000");
//        client = AdminClient.create(conf);
    }

    @PreDestroy
    void stop() {
//        client.close();
    }

    @Override
    public HealthCheckResponse call() {
        HealthCheckResponseBuilder builder = HealthCheckResponse.named("Couchbase connection health check").up();
        try {
//            StringBuilder nodes = new StringBuilder();
//            for (Node node : client.describeCluster().nodes().get()) {
//                if (nodes.length() > 0) {
//                    nodes.append(',');
//                }
//                nodes.append(node.host()).append(':').append(node.port());
//            }
            return builder.build();
        } catch (Exception e) {
            return builder.down().withData("reason", e.getMessage()).build();
        }
    }
}
