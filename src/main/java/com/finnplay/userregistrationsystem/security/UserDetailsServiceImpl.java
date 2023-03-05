package com.finnplay.userregistrationsystem.security;

import com.finnplay.userregistrationsystem.entity.User;
import com.finnplay.userregistrationsystem.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;

import static com.finnplay.userregistrationsystem.constants.ServerConstants.INVALID_LOGIN;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException(INVALID_LOGIN);
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                new HashSet<GrantedAuthority>());
    }
}
