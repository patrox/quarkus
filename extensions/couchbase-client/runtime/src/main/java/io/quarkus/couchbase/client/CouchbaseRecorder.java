package io.quarkus.couchbase.client;

import org.jboss.logging.Logger;

import io.quarkus.runtime.annotations.Recorder;

@Recorder
public class CouchbaseRecorder {

    private static final Logger log = Logger.getLogger(CouchbaseRecorder.class);

    //    public RuntimeValue<Driver> initializeDriver(CouchbaseConfiguration configuration, ShutdownContext shutdownContext) {
    //
    //        String uri = configuration.uri;
    //        AuthToken authToken = AuthTokens.none();
    //        if (!configuration.authentication.disabled) {
    //            authToken = AuthTokens.basic(configuration.authentication.username, configuration.authentication.password);
    //        }
    //
    //        Config.ConfigBuilder configBuilder = createBaseConfig();
    //        configureSsl(configBuilder, configuration);
    //        configurePoolSettings(configBuilder, configuration.pool);
    //
    //        Driver driver = GraphDatabase.driver(uri, authToken, configBuilder.build());
    //        shutdownContext.addShutdownTask(driver::close);
    //        return new RuntimeValue<>(driver);
    //    }
    //
    //    public void configureNeo4jProducer(BeanContainer beanContainer, RuntimeValue<Driver> driverHolder) {
    //        CouchbaseProducer driverProducer = beanContainer.instance(CouchbaseProducer.class);
    //        driverProducer.initialize(driverHolder.getValue());
    //    }
    //
    //    public Consumer<MetricsFactory> registerMetrics(CouchbaseConfiguration configuration) {
    //        if (configuration.pool != null && configuration.pool.metricsEnabled) {
    //            return new Consumer<MetricsFactory>() {
    //                @Override
    //                public void accept(MetricsFactory metricsFactory) {
    //                    // if the pool hasn't been used yet, the ConnectionPoolMetrics object doesn't exist, so use zeros instead
    //                    metricsFactory.builder("neo4j.acquired").buildCounter(
    //                            () -> getConnectionPoolMetrics().map(ConnectionPoolMetrics::acquired).orElse(0L));
    //                    metricsFactory.builder("neo4j.acquiring").buildCounter(
    //                            () -> getConnectionPoolMetrics().map(ConnectionPoolMetrics::acquiring).orElse(0));
    //                    metricsFactory.builder("neo4j.closed").buildCounter(
    //                            () -> getConnectionPoolMetrics().map(ConnectionPoolMetrics::closed).orElse(0L));
    //                    metricsFactory.builder("neo4j.created").buildCounter(
    //                            () -> getConnectionPoolMetrics().map(ConnectionPoolMetrics::created).orElse(0L));
    //                    metricsFactory.builder("neo4j.creating").buildCounter(
    //                            () -> getConnectionPoolMetrics().map(ConnectionPoolMetrics::creating).orElse(0));
    //                    metricsFactory.builder("neo4j.failedToCreate").buildCounter(
    //                            () -> getConnectionPoolMetrics().map(ConnectionPoolMetrics::failedToCreate).orElse(0L));
    //                    metricsFactory.builder("neo4j.timedOutToAcquire").buildCounter(
    //                            () -> getConnectionPoolMetrics().map(ConnectionPoolMetrics::timedOutToAcquire).orElse(0L));
    //                    metricsFactory.builder("neo4j.totalAcquisitionTime").buildCounter(
    //                            () -> getConnectionPoolMetrics().map(ConnectionPoolMetrics::totalAcquisitionTime).orElse(0L));
    //                    metricsFactory.builder("neo4j.totalConnectionTime").buildCounter(
    //                            () -> getConnectionPoolMetrics().map(ConnectionPoolMetrics::totalConnectionTime).orElse(0L));
    //                    metricsFactory.builder("neo4j.totalInUseCount").buildCounter(
    //                            () -> getConnectionPoolMetrics().map(ConnectionPoolMetrics::totalInUseCount).orElse(0L));
    //                    metricsFactory.builder("neo4j.totalInUseTime").buildCounter(
    //                            () -> getConnectionPoolMetrics().map(ConnectionPoolMetrics::totalInUseCount).orElse(0L));
    //                }
    //            };
    //        } else {
    //            return null;
    //        }
    //    }
    //
    //    // Until the pool is actually used for the first time, the ConnectionPoolMetrics object does not exist,
    //    // so this will be populated later
    //    private static Optional<ConnectionPoolMetrics> connectionPoolMetrics = Optional.empty();
    //
    //    private synchronized Optional<ConnectionPoolMetrics> getConnectionPoolMetrics() {
    //        if (!connectionPoolMetrics.isPresent()) {
    //            connectionPoolMetrics = Arc.container().instance(Driver.class)
    //                    .get()
    //                    .metrics()
    //                    .connectionPoolMetrics()
    //                    .stream()
    //                    .findFirst();
    //        }
    //        return connectionPoolMetrics;
    //    }
    //
    //    private static Config.ConfigBuilder createBaseConfig() {
    //        Config.ConfigBuilder configBuilder = Config.builder();
    //        Logging logging;
    //        try {
    //            logging = Logging.slf4j();
    //        } catch (Exception e) {
    //            logging = Logging.javaUtilLogging(Level.INFO);
    //        }
    //        configBuilder.withLogging(logging);
    //        return configBuilder;
    //    }
    //
    //    private static void configureSsl(Config.ConfigBuilder configBuilder,
    //            CouchbaseConfiguration configuration) {
    //
    //        // Disable encryption regardless of user configuration when ssl is not natively enabled.
    //        if (ImageInfo.inImageRuntimeCode() && !SslContextConfiguration.isSslNativeEnabled()) {
    //            log.warn(
    //                    "Native SSL is disabled, communication between this client and the Neo4j server cannot be encrypted.");
    //            configBuilder.withoutEncryption();
    //        } else {
    //            if (configuration.encrypted) {
    //                configBuilder.withEncryption();
    //                configBuilder.withTrustStrategy(configuration.trustSettings.toInternalRepresentation());
    //            } else {
    //                configBuilder.withoutEncryption();
    //            }
    //        }
    //    }
    //
    //    private static void configurePoolSettings(Config.ConfigBuilder configBuilder, CouchbaseConfiguration.Pool pool) {
    //
    //        if (log.isDebugEnabled()) {
    //            log.debug("Configuring Neo4j pool settings with " + pool);
    //        }
    //
    //        if (pool.logLeakedSessions) {
    //            configBuilder.withLeakedSessionsLogging();
    //        }
    //
    //        configBuilder.withMaxConnectionPoolSize(pool.maxConnectionPoolSize);
    //        configBuilder.withConnectionLivenessCheckTimeout(pool.idleTimeBeforeConnectionTest.toMillis(), MILLISECONDS);
    //        configBuilder.withMaxConnectionLifetime(pool.maxConnectionLifetime.toMillis(), MILLISECONDS);
    //        configBuilder.withConnectionAcquisitionTimeout(pool.connectionAcquisitionTimeout.toMillis(), MILLISECONDS);
    //
    //        if (pool.metricsEnabled) {
    //            configBuilder.withDriverMetrics();
    //        } else {
    //            configBuilder.withoutDriverMetrics();
    //        }
    //    }
}
