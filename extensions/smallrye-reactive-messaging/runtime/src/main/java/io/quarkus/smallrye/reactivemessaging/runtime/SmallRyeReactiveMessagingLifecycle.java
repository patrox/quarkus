package io.quarkus.smallrye.reactivemessaging.runtime;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.inject.spi.DefinitionException;
import jakarta.enterprise.inject.spi.DeploymentException;
import jakarta.inject.Inject;
import jakarta.interceptor.Interceptor;

import io.quarkus.runtime.StartupEvent;
import io.smallrye.reactive.messaging.providers.extension.ChannelConfiguration;
import io.smallrye.reactive.messaging.providers.extension.EmitterConfiguration;
import io.smallrye.reactive.messaging.providers.extension.MediatorManager;

@Dependent
public class SmallRyeReactiveMessagingLifecycle {

    @Inject
    MediatorManager mediatorManager;

    void onStaticInit(@Observes @Initialized(ApplicationScoped.class) Object event,
            SmallRyeReactiveMessagingRecorder.SmallRyeReactiveMessagingContext context,
            QuarkusWorkerPoolRegistry workerPoolRegistry) {
        mediatorManager.addAnalyzed(context.getMediatorConfigurations());
        for (WorkerConfiguration worker : context.getWorkerConfigurations()) {
            workerPoolRegistry.defineWorker(worker.getClassName(), worker.getMethodName(), worker.getPoolName());
        }
        for (EmitterConfiguration emitter : context.getEmitterConfigurations()) {
            mediatorManager.addEmitter(emitter);
        }
        for (ChannelConfiguration channel : context.getChannelConfigurations()) {
            mediatorManager.addChannel(channel);
        }
    }

    void onApplicationStart(@Observes @Priority(Interceptor.Priority.LIBRARY_BEFORE) StartupEvent event) {
        try {
            mediatorManager.start();
        } catch (Exception e) {
            if (e instanceof DeploymentException || e instanceof DefinitionException) {
                throw e;
            }
            throw new DeploymentException(e);
        }
    }

}
