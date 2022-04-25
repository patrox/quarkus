package io.quarkus.hibernate.reactive.runtime;

import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.Typed;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;

import org.hibernate.reactive.mutiny.Mutiny;

import io.quarkus.arc.DefaultBean;

public class ReactiveSessionFactoryProducer {

    @Inject
    @PersistenceUnit
    EntityManagerFactory emf;

    @Produces
    @Singleton
    @DefaultBean
    @Typed(Mutiny.SessionFactory.class)
    public Mutiny.SessionFactory mutinySessionFactory() {
        return emf.unwrap(Mutiny.SessionFactory.class);
    }

}
