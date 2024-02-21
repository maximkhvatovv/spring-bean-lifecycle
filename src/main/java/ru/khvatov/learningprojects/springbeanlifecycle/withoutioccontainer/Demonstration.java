package ru.khvatov.learningprojects.springbeanlifecycle.withoutioccontainer;

import ru.khvatov.learningprojects.springbeanlifecycle.pool.ConnectionPool;
import ru.khvatov.learningprojects.springbeanlifecycle.pool.ConnectionPoolImpl;
import ru.khvatov.learningprojects.springbeanlifecycle.repository.UserRepositoryImpl;
import ru.khvatov.learningprojects.springbeanlifecycle.service.UserServiceImpl;

public class Demonstration {
    public static void main(String[] args) {
        ConnectionPool connectionPool = new ConnectionPoolImpl();
        var userRepository = new UserRepositoryImpl(connectionPool);
        var userService = new UserServiceImpl(userRepository);
        System.out.println("UserService is ready for work.");
        System.out.println("But, you know, why do I have to do with all this stuff if I need only functionality of userService?");
    }
}
