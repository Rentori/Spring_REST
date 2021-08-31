package com.rentori.spring_rest.service.serviceImpl;

import com.rentori.spring_rest.model.User;
import com.rentori.spring_rest.repository.UserRepository;
import com.rentori.spring_rest.service.UserService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {
    private User user = new User();
    private List<User> userList = new ArrayList<>();
    
    @InjectMocks
    private UserService userService;
    
    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp() {
        userService.setUserRepository(userRepository);
        user.setId(1L);
        user.setUsername("test");
        userList.add(user);
    }

    @Test
    void save() {
        when(userRepository.save(any())).thenReturn(user);
        assertEquals(userService.register(user), user);
    }

    @Test
    void getAll() {
        when(userRepository.findAll()).thenReturn(userList);
        assertNotNull(userList);
        
        userService.getAll();
        verify(userRepository).findAll();
    }

    @Test
    void findByUsername() {
        when(userRepository.findByUsername(anyString())).thenReturn(user);
        assertEquals(userService.findByUsername("test"), user);
    }

    @Test
    void findById() {
        when(userRepository.getById(any())).thenReturn(user);
        assertEquals(userService.findById(1L), user);
    }

    @Test
    void deleteUser() {
        doNothing().when(userRepository).deleteById(any());
        userService.deleteUser(1L);
        
        verify(userRepository).deleteById(1L);
    }
}