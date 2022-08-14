/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.ac.silab.BookingApi.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.fon.ac.silab.BookingApi.domain.Event;
import rs.fon.ac.silab.BookingApi.service.EventService;

/**
 *
 * @author Vladimir
 */
@RestController()
@RequestMapping(path = "/organizator")
@ControllerAdvice
public class OrganizatorController {
    
    private final EventService eventService;
    
    @Autowired
    public OrganizatorController(EventService eventService) {
        this.eventService = eventService;
    }
    
    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<Event>> findMyEvents(Authentication authentication){
        
        String username = authentication.getName();
        
        
        return ResponseEntity.ok().body(eventService.findMyEvents(username));
       
    }
    
    
    
    
    
}
