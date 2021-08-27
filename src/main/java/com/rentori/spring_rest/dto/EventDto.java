package com.rentori.spring_rest.dto;

import com.rentori.spring_rest.model.Event;
import com.rentori.spring_rest.model.File;
import com.rentori.spring_rest.model.User;
import lombok.Data;

import java.util.Date;

@Data
public class EventDto {
    private Long id;
    private Long userId;
    private Long fileId;
    private Date created;
    private Date updated;
    
    public Event toEvent() {
        Event event = new Event();
        User user = new User();
        File file = new File();
        user.setId(userId);
        file.setId(fileId);
        event.setUser(user);
        event.setFile(file);
        
        return event;
    }
    
    public static EventDto fromEvent(Event event) {
        EventDto eventDto = new EventDto();
        eventDto.setId(event.getId());
        eventDto.setFileId(event.getFile().getId());
        eventDto.setUserId(event.getUser().getId());
        eventDto.setCreated(event.getCreated());
        eventDto.setUpdated(event.getUpdated());
        
        return eventDto;
    }
}
