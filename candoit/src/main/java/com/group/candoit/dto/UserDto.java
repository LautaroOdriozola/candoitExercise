package com.group.candoit.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotNull(message = "Invalid Name: Name is NULL")
    private String name;
    @NotNull(message = "Invalid Surname: Surname is NULL")
    private String surname;
    @NotNull(message = "Invalid Email: Email is NULL")
    @Email(message = "Invalid email")
    private String email;
    private String password;
    private String token;
}
