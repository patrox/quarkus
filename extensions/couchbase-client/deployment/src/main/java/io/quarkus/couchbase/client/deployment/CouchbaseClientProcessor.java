package io.quarkus.couchbase.client.deployment;

import io.quarkus.deployment.Feature;
import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.ExtensionSslNativeSupportBuildItem;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.smallrye.health.deployment.spi.HealthBuildItem;

class CouchbaseClientProcessor {

    @BuildStep
    FeatureBuildItem createFeature(BuildProducer<ExtensionSslNativeSupportBuildItem> extensionSslNativeSupport) {

        // Indicates that this extension would like the SSL support to be enabled
        extensionSslNativeSupport.produce(new ExtensionSslNativeSupportBuildItem(Feature.COUCHBASE));

        return new FeatureBuildItem(Feature.COUCHBASE);
    }

    //    @BuildStep
    //    AdditionalBeanBuildItem createDriverProducer() {
    //        return AdditionalBeanBuildItem.unremovableOf(Neo4jDriverProducer.class);
    //    }

    //    @BuildStep
    //    @Record(ExecutionTime.RUNTIME_INIT)
    //    CouchbaseClientBuildItem configureDriverProducer(Neo4jDriverRecorder recorder,
    //            BeanContainerBuildItem beanContainerBuildItem,
    //            Neo4jConfiguration configuration,
    //            ShutdownContextBuildItem shutdownContext) {
    //
    //        RuntimeValue<Driver> driverHolder = recorder.initializeDriver(configuration, shutdownContext);
    //        recorder.configureNeo4jProducer(beanContainerBuildItem.getValue(), driverHolder);
    //        return new CouchbaseClientBuildItem(driverHolder);
    //    }

    @BuildStep
    HealthBuildItem addHealthCheck(CouchbaseBuildTimeConfig buildTimeConfig) {
        return new HealthBuildItem("io.quarkus.couchbase.runtime.health.Neo4jHealthCheck",
                buildTimeConfig.healthEnabled);
    }

    //    @BuildStep
    //    @Record(ExecutionTime.RUNTIME_INIT)
    //    void metrics(Neo4jConfiguration configuration,
    //            Neo4jDriverRecorder recorder,
    //            BuildProducer<MetricsFactoryConsumerBuildItem> metrics) {
    //        Consumer<MetricsFactory> metricsFactoryConsumer = recorder.registerMetrics(configuration);
    //        // If metrics for neo4j are disabled, the returned consumer will be null,
    //        // but in a processor we can't know that (it's controlled by a runtime config property)
    //        // so the BuildItem might contain null and in that case will be ignored by the metrics recorder
    //        metrics.produce(new MetricsFactoryConsumerBuildItem(metricsFactoryConsumer));
    //    }

}
