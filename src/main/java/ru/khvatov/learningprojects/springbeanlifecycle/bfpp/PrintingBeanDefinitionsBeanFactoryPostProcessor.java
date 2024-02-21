package ru.khvatov.learningprojects.springbeanlifecycle.bfpp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Slf4j
public class PrintingBeanDefinitionsBeanFactoryPostProcessor
        implements BeanFactoryPostProcessor, Ordered, PriorityOrdered {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
            throws BeansException {
        log.debug("postProcessBeanFactory() is invoked");
        log.debug("---bean definitions---");
        Arrays.stream(beanFactory.getBeanDefinitionNames())
                .map(beanFactory::getBeanDefinition)
                .map(BeanDefinition::toString)
                .forEach(log::debug);
        log.debug("---bean definitions---");
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
