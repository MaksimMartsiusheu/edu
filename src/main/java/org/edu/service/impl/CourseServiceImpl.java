package org.edu.service.impl;

import lombok.RequiredArgsConstructor;
import org.edu.controller.dto.CourseCreateDto;
import org.edu.model.*;
import org.edu.publisher.CourseEventPublisher;
import org.edu.repository.CourseRepository;
import org.edu.repository.UserCourseRepository;
import org.edu.repository.UserRepository;
import org.edu.service.CourseService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UserCourseRepository userCourseRepository;
    private final CourseEventPublisher eventPublisher;
    private final UserRepository userRepository;

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public void create(CourseCreateDto courseCreateDto) {
        courseRepository.save(
                Course.builder()
                        .durationInWeeks(courseCreateDto.getDurationInWeeks())
                        .beginDate(courseCreateDto.getBeginDate())
                        .title(courseCreateDto.getTitle())
                        .build()
        );
    }

    @Override
    public void addUserToCourse(Long courseId, User user) {

        Course currentCourse = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));

        UserCourse userCourse = UserCourse.builder()
                .id(UserCourseId.builder()
                        .courseId(currentCourse.getId())
                        .userId(user.getId())
                        .build())
                .courseStatus(CourseStatus.STARTED)
                .course(currentCourse)
                .user(user)
                .build();

        currentCourse.getUserCourses().add(userCourse);

        courseRepository.saveAndFlush(currentCourse);
    }

    @Override
    public void changeStatus(Long courseId, Long userId, CourseStatus status) {
        UserCourseId userCourseId = UserCourseId.builder()
                .userId(userId)
                .courseId(courseId)
                .build();

        UserCourse userCourse = UserCourse.builder()
                .id(userCourseId)
                .user(userRepository.findById(userId).get())
                .course(courseRepository.findById(courseId).get())
                .courseStatus(status)
                .build();

        userCourseRepository.save(userCourse);
        eventPublisher.publish(userCourse);

    }


}
