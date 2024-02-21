package ru.khvatov.learningprojects.springbeanlifecycle.repository;

import ru.khvatov.learningprojects.springbeanlifecycle.entity.UserEntity;

public interface UserRepository extends CrudRepository<Long, UserEntity> {

}
