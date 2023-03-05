package com.finnplay.userregistrationsystem.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static com.finnplay.userregistrationsystem.constants.ServerConstants.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    @NotBlank(message = EMPTY_FIRSTNAME)
    @Pattern(regexp = "[a-zA-Z]*", message = INVALID_FIRSTNAME)
    private String firstName;
    @NotBlank(message = EMPTY_LASTNAME)
    @Pattern(regexp = "[a-zA-Z]*", message = INVALID_LASTNAME)
    private String lastName;
    @NotNull(message = EMPTY_BIRTHDATE)
    @Past(message = INVALID_BIRTHDATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthdate;
    @Email(
            message = INVALID_EMAIL,
            regexp =
                    "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
    @Email
    private String email;
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$", message = WEAK_PASSWORD)
    private String password;
}
