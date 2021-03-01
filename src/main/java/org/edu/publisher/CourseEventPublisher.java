package org.edu.publisher;

import org.edu.model.UserCourse;

public interface CourseEventPublisher {

    void publish(UserCourse userCourse);
}
