package com.rentori.spring_rest.service;

import com.rentori.spring_rest.model.User;
import com.rentori.spring_rest.repository.FileRepository;
import com.rentori.spring_rest.repository.UserRepository;

import java.util.List;

public interface UserService {
    User register(User user);
    
    List<User> getAll();
    
    User save(User user);
    
    User findByUsername(String username);
    
    User findById(Long id);
    
    void deleteUser(Long id);
    
    User updateUser(User entity);

    void setUserRepository(UserRepository userRepository);
}
