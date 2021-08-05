package com.rentori.spring_rest.rest.admin;

import com.rentori.spring_rest.dto.UserDto;
import com.rentori.spring_rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
            users.add(dto);
        });
        return ResponseEntity.ok(users);
    }
}
