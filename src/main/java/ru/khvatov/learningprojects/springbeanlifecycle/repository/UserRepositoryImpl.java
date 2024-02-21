package ru.khvatov.learningprojects.springbeanlifecycle.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.khvatov.learningprojects.springbeanlifecycle.bpp.proxywrappingexample.InTransaction;
import ru.khvatov.learningprojects.springbeanlifecycle.entity.UserEntity;
import ru.khvatov.learningprojects.springbeanlifecycle.pool.ConnectionPool;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@InTransaction
@Component
@Slf4j
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    public static final UserEntity SAMPLE_ENTITY = new UserEntity(1L);

    private final ConnectionPool connectionPool;

    @Override
    public Collection<UserEntity> findAll() {
        return Collections.emptyList();
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        return Optional.of(new UserEntity(id));
    }

    @Override
    public void delete(Long id) {
        System.out.printf("UserEntity with id=%d is deleted%n", id);
    }
}
