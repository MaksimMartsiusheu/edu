package org.edu.service;

import org.edu.controller.dto.UserCreateDto;
import org.edu.model.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    Long create(UserCreateDto userCreateDto);

    void update(User user);

    void deleteById(Long userId);
}
