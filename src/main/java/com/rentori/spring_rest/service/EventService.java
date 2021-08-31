package com.rentori.spring_rest.service;

import com.rentori.spring_rest.model.Event;
import com.rentori.spring_rest.model.File;
import com.rentori.spring_rest.repository.EventRepository;
import com.rentori.spring_rest.repository.FileRepository;

import java.util.List;

public interface EventService {
    Event getById(Long id);

    Event save(Event entity);

    List<Event> getAll();

    void setEventRepository(EventRepository eventRepository);
}
