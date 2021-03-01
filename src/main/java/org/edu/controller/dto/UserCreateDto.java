package org.edu.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@Builder
public class UserCreateDto {

    private String firstName;

    private String lastName;

    @Email
    @NotBlank
    private String email;
}
