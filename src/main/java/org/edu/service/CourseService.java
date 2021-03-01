package org.edu.service;

import org.edu.controller.dto.CourseChangeStatusRequest;
import org.edu.controller.dto.CourseCreateDto;
import org.edu.model.Course;
import org.edu.model.CourseStatus;
import org.edu.model.User;

import java.util.List;

public interface CourseService {

    List<Course> findAll();

    void create(CourseCreateDto courseCreateDto);

    void addUserToCourse(Long courseId, User user);

    void changeStatus(Long courseId, Long userId, CourseStatus status);
}
