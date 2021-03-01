package org.edu.service.impl;

import lombok.RequiredArgsConstructor;
import org.edu.controller.dto.UserCreateDto;
import org.edu.model.User;
import org.edu.repository.UserRepository;
import org.edu.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    @Override
    public List<User> findAll() {

        LOGGER.info("Find all users");

        return userRepository.findAll();
    }

    @Override
    public Long create(UserCreateDto userCreateDto) {

        LOGGER.info("Creating user with email: {}", userCreateDto.getEmail());

        User user = User.builder()
                .email(userCreateDto.getEmail())
                .firstName(userCreateDto.getFirstName())
                .lastName(userCreateDto.getLastName())
                .build();

        return userRepository.saveAndFlush(user)
                .getId();
    }

    @Override
    public void update(User user) {

        LOGGER.info("Update user {}", user.getId());

        userRepository.save(user);
    }

    @Override
    public void deleteById(Long userId) {

        LOGGER.info("Delete user with id: {}", userId);

        userRepository.deleteById(userId);
    }
}
