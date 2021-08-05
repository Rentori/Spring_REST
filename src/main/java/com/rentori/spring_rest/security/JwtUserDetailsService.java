package com.rentori.spring_rest.security;

import com.rentori.spring_rest.model.User;
import com.rentori.spring_rest.security.jwt.JwtUser;
import com.rentori.spring_rest.security.jwt.JwtUserFactory;
import com.rentori.spring_rest.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(@Lazy UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        
        if(user == null) {
            throw new UsernameNotFoundException("User with username " + username + " not found");
        }
        
        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("IN loadByUserName - user with username: {} successfully loaded", username);
        return jwtUser;
    }
}