package org.edu.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class CourseCreateDto {

    private LocalDate beginDate;
    private Integer durationInWeeks;
    private String title;
}
