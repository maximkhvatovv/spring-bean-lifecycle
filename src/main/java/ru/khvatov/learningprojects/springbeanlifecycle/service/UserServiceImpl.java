package ru.khvatov.learningprojects.springbeanlifecycle.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.khvatov.learningprojects.springbeanlifecycle.bpp.proxywrappingexample.InTransaction;
import ru.khvatov.learningprojects.springbeanlifecycle.entity.UserEntity;
import ru.khvatov.learningprojects.springbeanlifecycle.repository.UserRepository;
@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
}
