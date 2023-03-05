package com.finnplay.userregistrationsystem.helper;

import com.finnplay.userregistrationsystem.dto.UserDto;
import org.springframework.ui.Model;

import static com.finnplay.userregistrationsystem.constants.ServerConstants.ATTRIBUTE_NAMES.USER;

public class UserRegistrationSystemHelper {

    public static String errorResponse(Model model, UserDto userDto, String targetUrl){
        model.addAttribute(USER, userDto);
        return targetUrl;
    }
}
