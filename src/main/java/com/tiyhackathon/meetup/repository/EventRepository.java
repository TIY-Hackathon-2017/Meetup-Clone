package com.tiyhackathon.meetup.repository;

import com.tiyhackathon.meetup.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by David Turk on 8/9/17.
 */
@Repository
public interface EventRepository extends JpaRepository<Event, Integer>{
}
