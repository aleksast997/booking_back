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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.fon.ac.silab.BookingApi.domain.Booking;
import rs.fon.ac.silab.BookingApi.domain.User;
import rs.fon.ac.silab.BookingApi.service.BookingService;
import rs.fon.ac.silab.BookingApi.service.EventService;

/**
 *
 * @author Vladimir
 */
@RestController
@RequestMapping(path = "/booking")
@ControllerAdvice
@CrossOrigin(origins = "*")
public class BookingController {
    
    private final BookingService bookingService;
    
    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }
    
    @PostMapping()
    @CrossOrigin
    public ResponseEntity<Booking> saveBooking(@RequestBody Booking booking, Authentication authentication){
        String username = authentication.getName();
        User user = new User();
        user.setName(username);
        booking.setUser(user);
       return ResponseEntity.ok().body(bookingService.save(booking));
           
    }
    
    
    @CrossOrigin
    @GetMapping()
    public ResponseEntity<List<Booking>> findAll(Authentication authentication){
          String username = authentication.getName();
          return ResponseEntity.ok().body(bookingService.findAll(username));
        
    
    }
    
    
    @CrossOrigin
    @DeleteMapping("/{idEvent}")
    public ResponseEntity<?> deleteById(@PathVariable long idEvent, Authentication authentication){
            
        String username = authentication.getName();
        
        bookingService.deleteById(idEvent,username);
        
        return ResponseEntity.ok().body(null);
    }
    
    
    
}
