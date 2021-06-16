package io.quarkus.couchbase.client.deployment;

@ConfigRoot(name = "couchbase", phase = ConfigPhase.BUILD_TIME)
public class CouchbaseBuildTimeConfig {
    /**
     * Whether or not an health check is published in case the smallrye-health extension is present.
     */
    @ConfigItem(name = "health.enabled", defaultValue = "true")
    public boolean healthEnabled;
}
