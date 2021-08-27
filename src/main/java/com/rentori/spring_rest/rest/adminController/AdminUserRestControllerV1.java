package com.rentori.spring_rest.rest.adminController;

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
public class AdminUserRestControllerV1 {
    private final UserService userService;

    @Autowired
    public AdminUserRestControllerV1(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/v1/admin/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = new ArrayList<>();
        userService.getAll().forEach(user -> {
            UserDto dto = new UserDto();
            dto.setId(user.getId());
            dto.setUsername(user.getUsername());
            dto.setPassword(user.getPassword());
            dto.setEmail(user.getEmail());
            dto.setStatus(user.getStatus());
            dto.setCreated(user.getCreated());
            dto.setUpdated(user.getUpdated());
            users.add(dto);
        });
        return ResponseEntity.ok(users);
    }

    @GetMapping("/api/v1/admin/users/{id}")
    public ResponseEntity getUserById(@PathVariable Long id) {
        User user = userService.findById(id);
        user.setPassword(user.getPassword());
        user.setCreated(user.getCreated());
        user.setUpdated(user.getUpdated());
        return ResponseEntity.ok(UserDto.fromUser(user));
    }
    
    @PostMapping("api/v1/admin/users")
    public ResponseEntity registerUser(@RequestBody UserDto userDto) {
        User user = userDto.toUser();
        try {
            userService.register(user);
        } catch (IllegalArgumentException e) {
            throw e;
        }
        return new ResponseEntity(userDto, HttpStatus.CREATED);
    }
    
    @PutMapping("/api/v1/admin/users/{id}")
    public ResponseEntity updateUser(@RequestBody UserDto userDto) {
        User user = userDto.toUser();
        try {
            userService.updateUser(user);
        } catch (IllegalArgumentException e) {
            throw e;
        }
        
        return new ResponseEntity(userDto, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/api/v1/admin/users/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        User user = userService.findById(id);
        user.setStatus(Status.DELETED);
        userService.save(user);

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
