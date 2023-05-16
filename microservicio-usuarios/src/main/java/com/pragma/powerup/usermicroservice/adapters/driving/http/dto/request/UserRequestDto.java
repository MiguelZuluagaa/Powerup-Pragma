package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.RoleEntity;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserRequestDto {
    @NotEmpty(message = "The name cannot be empty")
    private String name;

    @NotEmpty(message = "The surname cannot be empty")
    private String surname;

    @NotEmpty(message = "The email cannot be empty")
    @Email(message = "The email is not valid")
    private String email;

    @NotEmpty(message = "The phone cannot be empty")
    @Size(max = 13, message = "The cell phone number must have a maximum of 13 digits")
    @Digits(integer=13, fraction=0, message="The phone field must contain only whole numbers")
    private String phone;

    @NotEmpty(message = "The dni cannot be empty")
    @Digits(integer=20, fraction=0, message="The dni must contain only integers")
    private String dni;

    @NotEmpty(message = "The birdDate cannot be empty")
    @Pattern( regexp = "^(?:3[01]|[12][0-9]|0?[1-9])([\\-/.])(0?[1-9]|1[1-2])\\1\\d{4}$",message = "Incorrect date format, valid date format (dd-mm-yyyy)")
    private String birdDate;

    @NotEmpty(message = "The password cannot be empty")
    private String password;

    /*@NotNull(message = "The idRole cannot be empty")
    private RoleEntity idRole;*/

}
