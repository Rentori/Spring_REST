package com.rentori.spring_rest.repository;

import com.rentori.spring_rest.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
