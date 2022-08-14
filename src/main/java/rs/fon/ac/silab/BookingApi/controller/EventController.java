/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.ac.silab.BookingApi.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.fon.ac.silab.BookingApi.domain.Event;
import rs.fon.ac.silab.BookingApi.domain.TicketsCategory;
import rs.fon.ac.silab.BookingApi.domain.User;
import rs.fon.ac.silab.BookingApi.service.EventService;

/**
 *
 * @author Vladimir
 */
@RestController()
@RequestMapping(path = "/event")
@ControllerAdvice
@CrossOrigin
public class EventController {
    
    private final EventService eventService;
    
    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }
   
    
    @CrossOrigin
    @GetMapping()
    public ResponseEntity<List<Event>> findAll(){
        
        return ResponseEntity.ok().body(eventService.findAll());
       
    }
    
   
    /*
    @CrossOrigin
    @GetMapping("/{idEvent}")
    public ResponseEntity<Event> findbyId(@PathVariable int idEvent){
        
        return ResponseEntity.ok().body(eventService.findById(idEvent));
      
    }
    */
    
    @CrossOrigin
    @PostMapping()
    public ResponseEntity<Event> addEvent(@RequestBody Event event, Authentication authentication){
        
        String username = authentication.getName();
        
        User user = new User();
        user.setName(username);
        event.setUser(user);
        
        int brojac = 0;
        for (TicketsCategory category : event.getTicketsCategory()) {
              
                category.setEvent(event);
                category.getId().setIdTicketsCategory(++brojac);
                
        }
            
            
       return ResponseEntity.ok().body(eventService.add(event));
            
      
        
    }
   
    
    @CrossOrigin
    @DeleteMapping("/{idEvent}")
    public void deleteByEventId(@PathVariable Long idEvent){
        
        eventService.deleteById(idEvent);
      
    }
    
   
      
    @CrossOrigin
    @PutMapping()
    public ResponseEntity<Event> updateEvent(@RequestBody Event event, Authentication authentication){
        
        String username = authentication.getName();
        
        User user = new User();
        user.setName(username);
        event.setUser(user);
        
        
        for (TicketsCategory category : event.getTicketsCategory()) {
              
                category.setEvent(event);
                
                
        }
        
            
       return ResponseEntity.ok().body(eventService.save(event));
            
      
        
    }
    
    
    
    
    
    
}
   
    
    

