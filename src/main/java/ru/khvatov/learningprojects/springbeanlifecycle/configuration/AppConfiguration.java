package ru.khvatov.learningprojects.springbeanlifecycle.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@ComponentScan(basePackages = {"ru.khvatov.learningprojects.springbeanlifecycle"})
@PropertySource("classpath:application.properties")
@Configuration
public class AppConfiguration {
    @Bean
    public String poolName(@Value("${pool.name}") String name) {
        return name;
    }
}
