package org.edu.repository;

import org.edu.model.UserCourse;
import org.edu.model.UserCourseId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCourseRepository extends JpaRepository<UserCourse, UserCourseId> {
}
