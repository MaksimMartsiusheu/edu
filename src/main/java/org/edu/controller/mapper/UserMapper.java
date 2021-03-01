package org.edu.controller.mapper;

import org.edu.controller.dto.UserCourseResponseDto;
import org.edu.controller.dto.UserResponseDto;
import org.edu.model.Course;
import org.edu.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserResponseDto mapToResponseDto(User user) {

        List<UserCourseResponseDto> userCourses = user.getUserCourses()
                .stream()
                .filter((course) -> course.getUser().equals(user))
                .map((userCourse) -> {
                    Course course = userCourse.getCourse();
                    return UserCourseResponseDto.builder()
                            .title(course.getTitle())
                            .beginDate(course.getBeginDate())
                            .id(course.getId())
                            .durationInWeeks(course.getDurationInWeeks())
                            .status(userCourse.getCourseStatus().toString())
                            .build();
                }).collect(Collectors.toList());

        return UserResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .courses(userCourses)
                .build();
    }

    public List<UserResponseDto> mapToListResponseDto(List<User> users) {
        return users.stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }
}
