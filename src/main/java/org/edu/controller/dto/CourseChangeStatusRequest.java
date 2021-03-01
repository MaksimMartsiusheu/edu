package org.edu.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

public class CourseChangeStatusRequest {

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
