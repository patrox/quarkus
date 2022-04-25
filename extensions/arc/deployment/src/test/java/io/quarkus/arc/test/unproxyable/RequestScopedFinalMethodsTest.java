package io.quarkus.arc.test.unproxyable;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.event.Event;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import io.quarkus.arc.Arc;
import io.quarkus.arc.ArcContainer;
import io.quarkus.arc.InstanceHandle;
import io.quarkus.arc.ManagedContext;
import io.quarkus.test.QuarkusUnitTest;

public class RequestScopedFinalMethodsTest {

    @RegisterExtension
    public static QuarkusUnitTest container = new QuarkusUnitTest()
            .withApplicationRoot((jar) -> jar
                    .addClasses(RequestScopedBean.class));

    @Test
    public void testRequestScopedBeanWorksProperly() {
        ArcContainer container = Arc.container();
        ManagedContext requestContext = container.requestContext();
        requestContext.activate();

        InstanceHandle<RequestScopedBean> handle = container.instance(RequestScopedBean.class);
        Assertions.assertTrue(handle.isAvailable());

        RequestScopedBean bean = handle.get();
        Assertions.assertNull(bean.getProp());
        bean.setProp(100);
        Assertions.assertEquals(100, bean.getProp());

        requestContext.terminate();
        requestContext.activate();

        handle = container.instance(RequestScopedBean.class);
        bean = handle.get();
        Assertions.assertTrue(handle.isAvailable());
        Assertions.assertNull(bean.getProp());
    }

    @RequestScoped
    static class RequestScopedBean {
        private Integer prop = null;

        public final Integer getProp() {
            return prop;
        }

        public final void setProp(Integer prop) {
            this.prop = prop;
        }
    }

    @Dependent
    static class StringProducer {

        @Inject
        Event<String> event;

        void fire(String value) {
            event.fire(value);
        }

    }

    @Singleton
    static class StringObserver {

        private List<Integer> events;

        @Inject
        RequestScopedBean requestScopedBean;

        @PostConstruct
        void init() {
            events = new CopyOnWriteArrayList<>();
        }

        void observeSync(@Observes Integer value) {
            Integer oldValue = requestScopedBean.getProp();
            Integer newValue = oldValue == null ? value : value + oldValue;
            requestScopedBean.setProp(newValue);
            events.add(newValue);
        }

        List<Integer> getEvents() {
            return events;
        }

    }
}
