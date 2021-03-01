package org.edu.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCourse {

    @EmbeddedId
    private UserCourseId id;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private User user;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("courseId")
    private Course course;

    @Enumerated(EnumType.STRING)
    private CourseStatus courseStatus;

}
