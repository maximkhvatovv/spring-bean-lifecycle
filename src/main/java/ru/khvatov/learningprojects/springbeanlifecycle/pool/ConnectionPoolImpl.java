package ru.khvatov.learningprojects.springbeanlifecycle.pool;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.khvatov.learningprojects.springbeanlifecycle.bpp.injectionexample.FieldInject;

@Component
@Slf4j
@ToString
public class ConnectionPoolImpl implements ConnectionPool {

    @FieldInject("poolName")
    private String name;

    public ConnectionPoolImpl() {
        log.debug("constructor is invoked.");
    }

    @Autowired
    public void setSize(@Value("${pool.size}") int size) {
        log.debug("set*() is invoked, size={}", size);
    }

    @PostConstruct
    void init() {
        log.debug("init() is invoked.");
    }

    @PreDestroy
    void destroy() {
        log.debug("destroy() is invoked.");
    }
}
