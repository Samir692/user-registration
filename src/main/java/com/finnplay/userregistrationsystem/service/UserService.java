package com.finnplay.userregistrationsystem.service;

import com.finnplay.userregistrationsystem.dto.UserDto;
import com.finnplay.userregistrationsystem.entity.User;

public interface UserService {

    User findUserByEmail(String email);

    boolean saveUser(UserDto userDto);

    User mapUserDtoToUser(User existingUser, UserDto userDto);

    User updateUser(User updatedUser);
}
