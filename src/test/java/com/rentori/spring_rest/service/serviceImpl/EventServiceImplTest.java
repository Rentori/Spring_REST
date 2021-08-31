package com.rentori.spring_rest.service.serviceImpl;

import com.rentori.spring_rest.model.Event;
import com.rentori.spring_rest.model.File;
import com.rentori.spring_rest.model.User;
import com.rentori.spring_rest.repository.EventRepository;
import com.rentori.spring_rest.service.EventService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class EventServiceImplTest {
    private Event event = new Event();
    private List<Event> eventList = new ArrayList<>();
    
    @InjectMocks
    private EventService eventService;
    
    @Mock
    private EventRepository eventRepository;
    
    @Mock
    private File file;
    
    @Mock
    private User user;

    @Before
    public void setUp() {
        event.setFile(file);
        event.setUser(user);
        eventList.add(event);
        eventService.setEventRepository(eventRepository);
    }

    @Test
    void getById() {
        when(eventRepository.getById(anyLong())).thenReturn(event);
        assertEquals(eventService.getById(1L), event);
    }

    @Test
    void save() {
        when(eventRepository.save(any())).thenReturn(event);
        assertEquals(eventService.save(event), event);
    }

    @Test
    void getAll() {
        when(eventRepository.findAll()).thenReturn(eventList);
        assertNotNull(eventList);
        
        eventService.getAll();
        verify(eventRepository).findAll();
    }
}