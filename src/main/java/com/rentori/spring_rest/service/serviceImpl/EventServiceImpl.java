package com.rentori.spring_rest.service.serviceImpl;

import com.rentori.spring_rest.model.Event;
import com.rentori.spring_rest.model.File;
import com.rentori.spring_rest.model.User;
import com.rentori.spring_rest.repository.EventRepository;
import com.rentori.spring_rest.repository.FileRepository;
import com.rentori.spring_rest.repository.UserRepository;
import com.rentori.spring_rest.service.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class EventServiceImpl implements EventService {
    private final UserRepository userRepository;
    private final FileRepository fileRepository;
    private EventRepository eventRepository;

    @Autowired
    public EventServiceImpl(UserRepository userRepository, 
                            FileRepository fileRepository, 
                            EventRepository eventRepository) {
        this.userRepository = userRepository;
        this.fileRepository = fileRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public void setEventRepository(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event getById(Long id) {
        Event event = eventRepository.getById(id);
        log.info("IN getById - eventId: {} successfully got", event.getId());
        
        return event;
    }

    @Override
    public Event save(Event entity) {
        User user = userRepository.getById(entity.getUser().getId());
        File file = fileRepository.getById(entity.getFile().getId());
        
        Event event = new Event();
        event.setUser(user);
        event.setFile(file);
        event.setCreated(new Date());
        event.setUpdated(new Date());
        eventRepository.save(event);
        log.info("IN save - eventId: {} successfully registered", event.getId());
        
        return event;
    }

    @Override
    public List<Event> getAll() {
        List<Event> result = eventRepository.findAll();
        log.info("IN getAll - {} events found", result.size());
        return result;
    }
}
