/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.ac.silab.BookingApi.service.impl;

import rs.fon.ac.silab.BookingApi.dao.Dao;
//import rs.fon.ac.silab.BookingApi.dao.impl.EventDaoJDBCImpl;
import rs.fon.ac.silab.BookingApi.domain.Event;
//import dto.EventDto;
//import dto.EventTypeDto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
//import mapper.impl.EventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import rs.fon.ac.silab.BookingApi.domain.User;
import rs.fon.ac.silab.BookingApi.exceptions.EventNotFoundException;
import rs.fon.ac.silab.BookingApi.exceptions.UserNotFoundException;
import rs.fon.ac.silab.BookingApi.repository.UserRepository;
import rs.fon.ac.silab.BookingApi.service.EventService;

/**
 *
 * @author Vladimir
 */
@Service
@Transactional
public class EventServiceImpl implements EventService {
    
    private Dao<Event> eventDao;
   // private EventMapper eventMapper;
    //@Qualifier(value = "EventDaoSpringJPA")
    private UserRepository userRepository;
    
    
    @Autowired
    public EventServiceImpl(@Qualifier(value = "eventDao")Dao<Event> eventDao, UserRepository userRepository) {
        this.eventDao = eventDao;
        this.userRepository = userRepository;
       // this.eventMapper = eventMapper;
    }
    
    
    @Override
    public Event save(Event event)  {
      
        User user = findUser(event.getUser().getName());
        if(user == null){
        
            throw new UserNotFoundException("User is not found");
        }
        
        event.setUser(user);
        
        
        return eventDao.update(event);
        
        
        
    }

    @Override
    public List<Event> findAll()  {
        List<Event> events = eventDao.findAll();
        List<Event> onlyActiveEvents = new ArrayList<>();
        System.out.println("Svi eventovi su " + events);
        for (Event event : events) {
            
            if(event.getDate().after(new Date())){
            
                onlyActiveEvents.add(event);
            }
            
            
        }
        
        System.out.println("Vratio eventove " + onlyActiveEvents);
                
       return  onlyActiveEvents;
        
    }

    @Override
    public Event findById(long idEvent) {
          Event et =  eventDao.findById(idEvent);
          
          if(et==null){
              
              throw new EventNotFoundException("Event not found");
          
          }
          
          return et;
    }

    @Override
    public Event add(Event event) {
      
        User user = findUser(event.getUser().getName());
        
        if(user == null){
        
            throw new UserNotFoundException("User is not found");
        }
        
        event.setUser(user);
        
        eventDao.save(event);
        return event;
    }

    @Override
    public void deleteById(long idEvent) {
        
        
       eventDao.deleteById(idEvent);
    }

    private User findUser(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public List<Event> findMyEvents(String username) {
     
        List<Event> events = eventDao.findAll();
        List<Event> onlyActiveEvents = new ArrayList<>();
        User user = userRepository.findByName(username);
        
        
        for (Event event : events) {
            
            if(event.getDate().after(new Date())){
               if(event.getUser().getIdUser() == user.getIdUser()){
                   
                    onlyActiveEvents.add(event);
            
            
            }
              
            }
            
            
        }
        
        
                
       return  onlyActiveEvents;
    }
    
}
