package ru.khvatov.learningprojects.springbeanlifecycle.bpp.proxywrappingexample;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.lang.System.out;

@Component
@Slf4j
public class InTransactionBeanPostProcessor implements BeanPostProcessor, Ordered {
    private final Map<String, Class<?>> beanToItsClass = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        log.debug("postProcessBeforeInitialization() is invoked for {}", bean);
        if (bean.getClass().isAnnotationPresent(InTransaction.class)) {
            beanToItsClass.put(beanName, bean.getClass());
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.debug("postProcessAfterInitialization() is invoked for {}", bean);
        return Optional.ofNullable(beanToItsClass.get(beanName))
                .map(originalClass -> wrapInTransactionalProxy(bean, originalClass))
                .orElse(bean);
    }

    private Object wrapInTransactionalProxy(Object bean, Class<?> originalClass) {
        try {
            return Proxy.newProxyInstance(originalClass.getClassLoader(), originalClass.getInterfaces(), (proxy, method, args) -> {
                if (isMethodIsNotDeclaredInOriginalClass(method, originalClass)) {
                    return method.invoke(bean, args);
                }
                out.println("Open transaction");
                try {
                    out.printf("exec %s\n", method.getName());
                    return method.invoke(bean, args);
                } finally {
                    out.println("Close transaction");
                }
            });
        } finally {
            log.debug("{} of {} class has been wrapped in transactional proxy", bean, originalClass);
        }
    }

    private static boolean isMethodIsNotDeclaredInOriginalClass(Method method, Class<?> originalClass) {
        return Arrays.stream(originalClass.getDeclaredMethods()).noneMatch(m -> m.getName().equals(method.getName()));
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
