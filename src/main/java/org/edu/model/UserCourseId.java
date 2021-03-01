package org.edu.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCourseId implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "course_id")
    private Long courseId;

}
