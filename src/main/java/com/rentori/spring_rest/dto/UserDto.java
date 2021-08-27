package com.rentori.spring_rest.dto;

import com.rentori.spring_rest.model.Status;
import com.rentori.spring_rest.model.User;
import lombok.Data;

import java.util.Date;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String email;
    private Status status;
    private Date created;
    private Date updated;

    public User toUser() {
        User user = new User();
        user.setUsername(username);
        user.setId(id);
        user.setPassword(password);
        user.setEmail(email);
        user.setUpdated(new Date());
        user.setCreated(new Date());
        
        return user;
    }

    public static UserDto fromUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setPassword(user.getPassword());
        userDto.setStatus(user.getStatus());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setCreated(user.getCreated());
        userDto.setUpdated(user.getUpdated());
        
        return userDto;
    }
}
