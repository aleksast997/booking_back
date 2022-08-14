/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.ac.silab.BookingApi.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.fon.ac.silab.BookingApi.domain.EventType;
import rs.fon.ac.silab.BookingApi.service.EventTypeService;

/**
 *
 * @author Vladimir
 */
@RestController()
@RequestMapping(path = "/eventType")
public class EventTypeController {
    
    private final EventTypeService eventTypeService;

    @Autowired
    public EventTypeController(EventTypeService eventTypeService) {
        this.eventTypeService = eventTypeService;
    }
    
    @CrossOrigin
    @GetMapping()
    public ResponseEntity<List<EventType>> getAll(){
    
        return ResponseEntity.ok().body(eventTypeService.findAll());
    
        
    }
    
    
    
}
