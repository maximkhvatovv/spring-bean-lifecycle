package ru.khvatov.learningprojects.springbeanlifecycle.repository;

import java.util.Collection;
import java.util.Optional;

public interface CrudRepository<K, E>{
    Collection<E> findAll();

    Optional<E> findById(K id);

    void delete(K id);
}
