package org.edu.repository;

import org.edu.model.CourseEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<CourseEvent, Long> {
}
