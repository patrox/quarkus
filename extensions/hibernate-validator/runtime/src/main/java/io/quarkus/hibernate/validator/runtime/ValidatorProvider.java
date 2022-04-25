package io.quarkus.hibernate.validator.runtime;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@ApplicationScoped
public class ValidatorProvider {

    @Produces
    @Named("quarkus-hibernate-validator-factory")
    public ValidatorFactory factory() {
        return ValidatorHolder.getValidatorFactory();
    }

    @Produces
    public Validator validator() {
        return ValidatorHolder.getValidator();
    }
}
