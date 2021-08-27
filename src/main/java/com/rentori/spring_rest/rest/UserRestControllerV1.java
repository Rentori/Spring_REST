package com.rentori.spring_rest.rest;

import com.rentori.spring_rest.dto.UserDto;
import com.rentori.spring_rest.model.Status;
import com.rentori.spring_rest.model.User;
import com.rentori.spring_rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserRestControllerV1 {
    private final UserService userService;

    @Autowired
    public UserRestControllerV1(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("api/v1/users")
    public ResponseEntity registerUser(@RequestBody UserDto userDto) {
        User user = userDto.toUser();
        try {
            userService.register(user);
        } catch (IllegalArgumentException e) {
            throw e;
        }
        return new ResponseEntity(userDto, HttpStatus.CREATED);
    }

    @GetMapping("/api/v1/users")
    public ResponseEntity<List<UserDto>> getUsers() {
        List<UserDto> userDtoList = new ArrayList<>();
        userService.getAll().forEach(user -> {
            UserDto userDto = new UserDto();
            userDto.setUsername(user.getUsername());
            userDto.setId(user.getId());
            userDto.setEmail(user.getEmail());
            userDtoList.add(userDto);
        });
        return ResponseEntity.ok(userDtoList);
    }

    @GetMapping("/avi/v1/users/{id}")
    public ResponseEntity getUserById(@PathVariable Long id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(UserDto.fromUser(user));
    }

    @DeleteMapping("/api/v1/users/{id}")
    public ResponseEntity deleteUserById(@PathVariable Long id) {
        User user = userService.findById(id);
        user.setStatus(Status.DELETED);
        userService.save(user);

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    @PutMapping("/api/v1/users")
    public ResponseEntity updateUser(@RequestBody UserDto userDto) {
        User user = userDto.toUser();
        try {
            userService.updateUser(user);
        } catch (IllegalArgumentException e) {
            throw e;
        }

        return new ResponseEntity(userDto, HttpStatus.ACCEPTED);
    }
}
