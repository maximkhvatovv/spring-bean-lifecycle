package ru.khvatov.learningprojects.springbeanlifecycle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.khvatov.learningprojects.springbeanlifecycle.configuration.AppConfiguration;
import ru.khvatov.learningprojects.springbeanlifecycle.repository.UserRepository;

public class SpringApplication {
    public static void main(String[] args) {
        var applicationContext = new AnnotationConfigApplicationContext(AppConfiguration.class);
        applicationContext.registerShutdownHook();

        var userRepository = applicationContext.getBean("userRepositoryImpl", UserRepository.class);
        var all = userRepository.findAll();

    }
}
