package org.edu.controller;

import lombok.RequiredArgsConstructor;
import org.edu.controller.dto.CourseChangeStatusRequest;
import org.edu.controller.dto.CourseCreateDto;
import org.edu.model.CourseStatus;
import org.edu.model.User;
import org.edu.service.CourseService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    private void addCourse(@RequestBody CourseCreateDto courseCreateDto) {
        courseService.create(courseCreateDto);
    }

    @PostMapping("/{courseId}/users")
    private void addUserToCourse(@PathVariable Long courseId, @RequestBody User user) {
        courseService.addUserToCourse(courseId, user);
    }

    @PostMapping("/{courseId}/users/{userId}/status")
    private void changeStatus(
            @RequestBody CourseChangeStatusRequest statusRequest,
            @PathVariable Long courseId,
            @PathVariable Long userId
    ) {
        courseService.changeStatus(courseId, userId, CourseStatus.valueOf(statusRequest.getStatus()));

    }

}
