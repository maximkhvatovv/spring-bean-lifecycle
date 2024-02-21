package ru.khvatov.learningprojects.springbeanlifecycle.bpp.injectionexample;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class FieldInjectBeanPostProcessor implements BeanPostProcessor, ApplicationContextAware, Ordered {
    private ApplicationContext context;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        log.debug("postProcessBeforeInitialization() is invoked for {}", bean);
        List<Field> fieldsForInjection = Arrays.stream(bean.getClass().getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(FieldInject.class))
                .toList();
        for (Field field : fieldsForInjection) {
            FieldInject annotation = field.getAnnotation(FieldInject.class);
            final String beanNameToBeInjected = annotation.value();


            Object beanToBeInjected = getBeanFromAppContext(beanNameToBeInjected);
            field.setAccessible(true);
            injectBeanIntoField(beanToBeInjected, field, bean);
            log.debug("{} is injected into {} postProcessBeforeInitialization() is invoked", beanToBeInjected, bean);
        }
        return bean;
    }

    private Object getBeanFromAppContext(String beanNameToBeInjected) {
        try {
            return context.getBean(beanNameToBeInjected);
        } catch (NoSuchBeanDefinitionException exception) {
            log.error("Failed to inject bean because of {}", exception.getMessage(), exception);
            throw new RuntimeException("Failed to inject bean because %s".formatted(exception), exception);
        }
    }

    private void injectBeanIntoField(Object beanToBeInjected, Field patientBeanField, Object patientBean) {
        try {
            patientBeanField.set(patientBean, beanToBeInjected);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
