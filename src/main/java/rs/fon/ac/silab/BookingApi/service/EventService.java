/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.ac.silab.BookingApi.service;

import rs.fon.ac.silab.BookingApi.domain.Event;

import java.util.List;

/**
 *
 * @author Vladimir
 */
public interface EventService {
    
    Event save(Event event);
    List<Event> findAll();
    Event findById(long idEvent);
    Event add(Event event);
    void deleteById(long idEvent);
    List<Event> findMyEvents(String username);
    
}
