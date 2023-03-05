package com.finnplay.userregistrationsystem.controller;

import com.finnplay.userregistrationsystem.dto.UserDto;
import com.finnplay.userregistrationsystem.entity.User;
import com.finnplay.userregistrationsystem.service.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.validation.BindingResult;

import java.security.Principal;

import static com.finnplay.userregistrationsystem.constants.ServerConstants.*;
import static com.finnplay.userregistrationsystem.constants.ServerConstants.ATTRIBUTE_NAMES.*;
import static com.finnplay.userregistrationsystem.constants.ServerConstants.ENDPOINTS.*;
import static com.finnplay.userregistrationsystem.helper.UserRegistrationSystemHelper.errorResponse;

@Controller
public class UserController {

    private UserService userService;
    private ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(ROOT)
    public String home(){
        return INDEX;
    }

    @GetMapping(LOGIN_MAPPING)
    public String login(){
        return LOGIN;
    }

    @GetMapping(USER_INFO_MAPPING)
    public String userinfo(Principal authenticatedUser, Model model){
        User user = userService.findUserByEmail(authenticatedUser.getName());
        UserDto userDto = modelMapper.map(user, UserDto.class);
        userDto.setPassword(null);
        model.addAttribute(USER, userDto);
        return USER_INFO;
    }

    @PostMapping(USER_INFO_SAVE_MAPPING)
    public String saveUserinfo(@Valid @ModelAttribute(USER) UserDto userDto, BindingResult result, Principal principal, Model model){
        if(result.hasErrors()){
            boolean isFalseErrorPasswordField = userDto.getPassword().isEmpty() && result.getFieldError(PASSWORD) != null;
            if(!isFalseErrorPasswordField || result.getErrorCount() > 1)
                return errorResponse(model, userDto, USER_INFO);
        }

        User existingUser = userService.findUserByEmail(principal.getName());
        User updatedUser = userService.mapUserDtoToUser(existingUser, userDto);
        // if there were no changes in the data return message
        if(updatedUser == null){
            return REDIRECT_USER_INFO_IS_SAME;
        }
        updatedUser = userService.updateUser(updatedUser);

        // email is the primary identifier, user needs to login again if email is changed
        if(updatedUser == null || !principal.getName().equals(updatedUser.getEmail())){
            return REDIRECT_LOGIN_EMAIL_CHANGED;
        }
        userDto.setPassword(null);
        model.addAttribute(USER, userDto);
        return REDIRECT_USER_INFO_SUCCESS;
    }

    @GetMapping(REGISTER_MAPPING)
    public String registerForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute(USER, user);
        return REGISTER;
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute(USER) UserDto userDto, BindingResult result, Model model) {
        if(result.hasErrors()){
            return errorResponse(model, userDto, REGISTER);
        }
        User existingUser = userService.findUserByEmail(userDto.getEmail());
        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue(EMAIL, null,
                    DUPLICATE_ACCOUNT);
        }
        if(result.hasErrors()){
            return errorResponse(model, userDto, REGISTER);
        }
        if(!userService.saveUser(userDto)){
            result.reject(ERROR_GENERIC_CODE, ERROR_GENERIC_SERVER);
        }
        return REDIRECT_USER_INFO;
    }
}
