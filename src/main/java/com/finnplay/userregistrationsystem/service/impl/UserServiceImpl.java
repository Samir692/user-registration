package com.finnplay.userregistrationsystem.service.impl;

import com.finnplay.userregistrationsystem.dto.UserDto;
import com.finnplay.userregistrationsystem.entity.User;
import com.finnplay.userregistrationsystem.repository.UserRepository;
import com.finnplay.userregistrationsystem.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private ModelMapper modelMapper;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean saveUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        try{
            userRepository.save(user);
            return true;
        }
        catch (DataAccessException ex){
            logger.error(ex.getMessage());
            return false;
        }
    }

    @Override
    public User mapUserDtoToUser(User existingUser, UserDto incomingUserDto){
        User user = modelMapper.map(existingUser, User.class);
        int changesCounter = 0;
        if(!existingUser.getFirstName().equals(incomingUserDto.getFirstName())){
            user.setFirstName(incomingUserDto.getFirstName());
            changesCounter+=1;
        }
        if(!existingUser.getLastName().equals(incomingUserDto.getLastName())){
            user.setLastName(incomingUserDto.getLastName());
            changesCounter+=1;
        }
        if(!(existingUser.getBirthdate().compareTo(incomingUserDto.getBirthdate()) == 0)){
            user.setBirthdate(incomingUserDto.getBirthdate());
            changesCounter+=1;
        }
        if(!incomingUserDto.getPassword().isEmpty() || passwordEncoder.encode(incomingUserDto.getPassword()).equals(existingUser.getPassword())) {
            user.setPassword(passwordEncoder.encode(incomingUserDto.getPassword()));
            changesCounter+=1;
        }
        if(!existingUser.getEmail().equals(incomingUserDto.getEmail())){
            user.setEmail(incomingUserDto.getEmail());
            changesCounter+=1;
        }
        if(changesCounter == 0){
            return null;
        }
        return user;
    }

    @Override
    public User updateUser(User updatedUser) {
        try{
            return userRepository.save(updatedUser);
        }
        catch (DataAccessException ex){
            logger.error(ex.getMessage());
            return null;
        }
    }
}
