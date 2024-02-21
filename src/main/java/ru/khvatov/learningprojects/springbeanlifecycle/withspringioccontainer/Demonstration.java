package ru.khvatov.learningprojects.springbeanlifecycle.withspringioccontainer;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.khvatov.learningprojects.springbeanlifecycle.configuration.AppConfiguration;
import ru.khvatov.learningprojects.springbeanlifecycle.service.UserService;

public class Demonstration {
    public static void main(String[] args) {
        var applicationContext = new AnnotationConfigApplicationContext(AppConfiguration.class);

        var userService = applicationContext.getBean("userServiceImpl", UserService.class);

    }
}
