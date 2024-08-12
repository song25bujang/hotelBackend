package org.spring.middleproject.hotelback.service;

import org.spring.middleproject.hotelback.DTO.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService USER_SERVICE;
    private final BCryptPasswordEncoder passwordEncoder;
    @Autowired
    public UserDetailsServiceImpl(UserService userService, BCryptPasswordEncoder passwordEncoder) {
        USER_SERVICE = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username: " + username);
        UserDTO userDTO = USER_SERVICE.selectByUsername(username);
        if (userDTO == null) {
            throw new UsernameNotFoundException(username + "is not a valid username");
        }
        System.out.println("Encoded Password in DB: " + userDTO.getPassword());
        return userDTO;
    }


}
