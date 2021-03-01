package org.edu.publisher.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.edu.config.PubSubConfig;
import org.edu.model.CourseEvent;
import org.edu.model.EventStatus;
import org.edu.model.UserCourse;
import org.edu.publisher.CourseEventPublisher;
import org.edu.repository.EventRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PubSubCourseEventPublisher implements CourseEventPublisher {

    private final PubSubConfig.GCPEventSender eventSender;
    private final ObjectMapper objectMapper;
    private final EventRepository eventRepository;

    @Override
    public void publish(UserCourse userCourse) {

        try {
            CourseEvent event = CourseEvent.builder()
                    .courseStatus(userCourse.getCourseStatus())
                    .userEmail(userCourse.getUser().getEmail())
                    .courseTitle(userCourse.getCourse().getTitle())
                    .eventStatus(EventStatus.IN_PROGRESS)
                    .build();

            eventRepository.saveAndFlush(event);

            eventSender.sendToPubsub(
                    objectMapper.writeValueAsString(event)
            );
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
