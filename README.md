# Bean Lifecycle
Rough pseudocode:
```
Read configs(xml-based, annotation-based, java-based)

Construct BeanDefinitions

Pass each BeanDefinition through BeanFactoryPostProcessors

Sort BeanDefinitions

for each BeanDefinition{
  - Invoke Bean Constructor
  for each Bean{
    - Invoke Setters
    - pass each bean through postProcessBeforeInitialization() of each BeanPostProcessor
    - Invoke Initialization Callbacks:
       1. @PostConstuct
       2. afterPropertiesSet() from InitializingBean
       3. init-method from xml-config
    - pass each bean through postProcessAfterInitialization() of each BeanPostProcessor (wrapping beans in proxy)
  }
}

Fully configured Singleton beans are stored in BeanFactory map `singletonObjects` (located in DefaultSingletonBeanRegistry)

On calling close() method in ConfigurableApplicationContext object:
  for each SingletonBean{
      - Invoke Destruction Callbacks:
        1. @PreDestroy
        2. destroy from DisposableBean
        3. destroy-method from xml-config
  }
```
