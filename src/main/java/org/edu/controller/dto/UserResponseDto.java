package org.edu.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserResponseDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<UserCourseResponseDto> courses;
}
