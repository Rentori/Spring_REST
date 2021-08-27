package com.rentori.spring_rest.rest.adminController;

import com.rentori.spring_rest.dto.EventDto;
import com.rentori.spring_rest.model.Event;
import com.rentori.spring_rest.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AdminEventRestControllerV1 {
    private final EventService eventService;

    @Autowired
    public AdminEventRestControllerV1(EventService eventService) {
        this.eventService = eventService;
    }


    @PostMapping("/api/v1/users/events")
    public ResponseEntity saveEvent(@RequestBody EventDto eventDto) {
        Event event = eventDto.toEvent();
        eventService.save(event);

        return new ResponseEntity(eventDto, HttpStatus.CREATED);
    }

    @GetMapping("/api/v1/users/events")
    public ResponseEntity<List<EventDto>> getEvents() {
        List<EventDto> eventDtoList = new ArrayList<>();
        eventService.getAll().forEach(event -> {
            EventDto eventDto = new EventDto();
            eventDto.setUserId(event.getUser().getId());
            eventDto.setFileId(event.getFile().getId());
            eventDto.setId(event.getId());
            eventDto.setCreated(event.getCreated());
            eventDto.setUpdated(event.getUpdated());
            eventDtoList.add(eventDto);
        });
        return ResponseEntity.ok(eventDtoList);
    }

    @GetMapping("/api/v1/users/events/{id}")
    public ResponseEntity getEventById(@PathVariable Long id) {
        Event event = eventService.getById(id);
        return ResponseEntity.ok(EventDto.fromEvent(event));
    }
}
