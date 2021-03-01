package org.edu.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userEmail;
    private String courseTitle;

    @Enumerated(EnumType.STRING)
    private CourseStatus courseStatus;

    private final LocalDate date = LocalDate.now();

    @Enumerated(EnumType.STRING)
    private EventStatus eventStatus;

}
