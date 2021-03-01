package org.edu.controller;

import lombok.RequiredArgsConstructor;
import org.edu.controller.dto.UserCreateDto;
import org.edu.controller.dto.UserResponseDto;
import org.edu.controller.mapper.UserMapper;
import org.edu.model.User;
import org.edu.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        return userMapper.mapToListResponseDto(
                userService.findAll()
        );
    }

    @PostMapping
    public void addUser(@RequestBody UserCreateDto userCreateDto) {
        userService.create(userCreateDto);
    }

    @PutMapping()
    public void updateUser(@RequestBody User user) {
        userService.update(user);
    }

    @DeleteMapping("/{userId}")
    public void deleteById(@PathVariable Long userId) {
        userService.deleteById(userId);
    }

}
