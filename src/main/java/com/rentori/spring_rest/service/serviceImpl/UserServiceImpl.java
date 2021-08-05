package com.rentori.spring_rest.service.serviceImpl;

import com.rentori.spring_rest.model.Role;
import com.rentori.spring_rest.model.Status;
import com.rentori.spring_rest.model.User;
import com.rentori.spring_rest.repository.RoleRepository;
import com.rentori.spring_rest.repository.UserRepository;
import com.rentori.spring_rest.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
                
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);
        User registeredUser = userRepository.save(user);
        
        log.info("IN register - user: {} successfully registered", registeredUser);
        
        return registeredUser;
    }

    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        
        log.info("IN getAll - {} users found", result.size());
        
        return result;
    }

    @Override
    public User findByUsername(String username) {
        User result = userRepository.findByUsername(username);
        log.info("IN findByUserName - user: {} found by username", result.getUsername());
        return result;
    }

    @Override
    public User findById(Long id) {
        User result = userRepository.getById(id);
        log.info("IN findById - user: {} found by id", result);
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
        log.info("IN deleteById - user with id: {} successfully delete");
    }
}
