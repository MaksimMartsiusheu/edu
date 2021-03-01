package org.edu.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserCourseResponseDto {

    private Long id;
    private LocalDate beginDate;
    private Integer durationInWeeks;
    private String title;
    private String status;
}
