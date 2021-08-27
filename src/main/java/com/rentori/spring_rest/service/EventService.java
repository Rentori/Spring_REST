package com.rentori.spring_rest.service;

import com.rentori.spring_rest.model.Event;
import com.rentori.spring_rest.model.File;

import java.util.List;

public interface EventService {
    Event getById(Long id);

    Event save(Event entity);

    List<Event> getAll();
}
