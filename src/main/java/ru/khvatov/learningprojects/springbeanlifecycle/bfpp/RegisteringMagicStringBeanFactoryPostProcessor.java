package ru.khvatov.learningprojects.springbeanlifecycle.bfpp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.stereotype.Component;

import static org.springframework.beans.factory.support.BeanDefinitionBuilder.genericBeanDefinition;

@Component
@Slf4j
public class RegisteringMagicStringBeanFactoryPostProcessor
        implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
            throws BeansException {
        var beanDefinition = genericBeanDefinition(String.class)
                .addConstructorArgValue("I'm a Magic String")
                .getBeanDefinition();

        var beanDefinitionRegistry = (BeanDefinitionRegistry) beanFactory;
        beanDefinitionRegistry.registerBeanDefinition(
                "magicString",
                beanDefinition
        );
        log.debug("{} has been registered", beanDefinition);
    }
}
