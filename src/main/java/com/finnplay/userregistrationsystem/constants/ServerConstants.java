package com.finnplay.userregistrationsystem.constants;

public interface ServerConstants {
    String DUPLICATE_ACCOUNT = "There is already an existing user registered with the same email address. Please enter a new email address!!";
    String ERROR_GENERIC_SERVER = "There was an error in the server, please contact administrator for help!!";
    String ERROR_GENERIC_CODE = "500";
    String EMPTY_FIRSTNAME = "First name should not be empty";
    String INVALID_FIRSTNAME = "First Name can only have letters";
    String EMPTY_LASTNAME = "Last name should not be empty";
    String INVALID_LASTNAME = "Last Name can only have letters";
    String EMPTY_BIRTHDATE = "Birthdate should not be empty";
    String INVALID_BIRTHDATE = "Date of birth should be in the past";
    String INVALID_EMAIL = "Email is not valid";
    String WEAK_PASSWORD = "Please use strong password! At least one lowercase and one uppercase letter, one digit and one special character!";
    String INVALID_LOGIN = "Invalid username or password, please try again with correct credentials";

    interface ENDPOINTS {
        String ROOT = "/";
        String REGISTER = "register";
        String REGISTER_MAPPING = ROOT + REGISTER;
        String LOGIN = "login";
        String LOGIN_MAPPING = ROOT + LOGIN;
        String INDEX = "index";
        String USER_INFO = "userinfo";
        String USER_INFO_MAPPING = ROOT + USER_INFO;
        String USER_INFO_SAVE_MAPPING = ROOT + USER_INFO + "/save";
        String REDIRECT = "redirect:/";
        String REDIRECT_USER_INFO = REDIRECT + USER_INFO;
        String REDIRECT_USER_INFO_IS_SAME = REDIRECT_USER_INFO+ "?isSame";
        String REDIRECT_LOGIN_EMAIL_CHANGED = REDIRECT + LOGIN + "?emailChanged";
        String REDIRECT_USER_INFO_SUCCESS = REDIRECT_USER_INFO + "?success";

    }
    interface ATTRIBUTE_NAMES {
        String USER = "user";
        String EMAIL = "email";
        String PASSWORD = "password";
    }
}
