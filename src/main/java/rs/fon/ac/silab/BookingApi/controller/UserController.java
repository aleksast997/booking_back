/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.ac.silab.BookingApi.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.fon.ac.silab.BookingApi.domain.Event;
import rs.fon.ac.silab.BookingApi.domain.Role;
import rs.fon.ac.silab.BookingApi.domain.User;
import rs.fon.ac.silab.BookingApi.service.EventService;
import rs.fon.ac.silab.BookingApi.service.UserService;

/**
 *
 * @author Vladimir
 */
@ControllerAdvice
@RestController

@RequestMapping(path="/user")
public class UserController {
     private final UserService userService;
    
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
  
    @CrossOrigin
    @GetMapping()
    public ResponseEntity<List<User>>findAll(){
    
        return ResponseEntity.ok().body(userService.findAll());
    
    }
    
    @CrossOrigin
    @PostMapping()
    //URI for creation?
     public ResponseEntity<User>saveUser(@RequestBody User user){
    
        return ResponseEntity.ok().body(userService.saveUser(user));
    
    }
     
     
  
    

    
    
    
    
}
