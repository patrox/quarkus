package io.quarkus.qute.runtime.extensions;

import jakarta.enterprise.inject.Vetoed;

import io.quarkus.qute.TemplateExtension;

@Vetoed // Make sure no bean is created from this class
@TemplateExtension
public class NumberTemplateExtensions {

    static Integer mod(Integer number, Integer mod) {
        return number % mod;
    }

}
