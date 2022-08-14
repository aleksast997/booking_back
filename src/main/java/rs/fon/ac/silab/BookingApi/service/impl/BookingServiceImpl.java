/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.ac.silab.BookingApi.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale.Category;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import rs.fon.ac.silab.BookingApi.dao.Dao;
import rs.fon.ac.silab.BookingApi.domain.Booking;
import rs.fon.ac.silab.BookingApi.domain.Event;
import rs.fon.ac.silab.BookingApi.domain.TicketsCategory;
import rs.fon.ac.silab.BookingApi.domain.User;
import rs.fon.ac.silab.BookingApi.exceptions.AllSeatsTakenException;
import rs.fon.ac.silab.BookingApi.exceptions.BookingNotFoundException;
import rs.fon.ac.silab.BookingApi.exceptions.CategoryNotFoundException;
import rs.fon.ac.silab.BookingApi.exceptions.EventNotFoundException;
import rs.fon.ac.silab.BookingApi.exceptions.EventPassedException;
import rs.fon.ac.silab.BookingApi.exceptions.TicketAlreadyBookedException;
import rs.fon.ac.silab.BookingApi.repository.UserRepository;

import rs.fon.ac.silab.BookingApi.service.BookingService;

/**
 *
 * @author Vladimir
 */
@Service
@Transactional
public class BookingServiceImpl implements BookingService{

      private Dao<Booking> bookingDao;
      private Dao<Event> eventDao;
      private UserRepository userRepository;
    
    @Autowired
    public BookingServiceImpl( Dao<Booking> userDao, Dao<Event> eventDao, UserRepository userRepository) {
        this.eventDao = eventDao;
        this.bookingDao = userDao;
        this.userRepository = userRepository;
    }
    
    
    @Override
    public Booking save(Booking booking) {
       booking= addUser(booking);
        checkIfEventPassed(booking);
        checkIfBooked(booking);
        handleSeats(booking);
        
        return bookingDao.save(booking);
    }

    @Override
    public List<Booking> findAll(String username) {
        List<Booking> allBookings = bookingDao.findAll();
        List<Booking> myBookings = findByName(allBookings, username);
        
        return myBookings;
    }

    @Override
    public void deleteById(long idEvent, String username) {
        User user = userRepository.findByName(username);
       
                
        
        
        freeSeats(idEvent, user.getIdUser());
        bookingDao.deleteByCompositeId(idEvent, user.getIdUser());
    }

    
    
    private void checkIfBooked(Booking booking) {
       
        List<Booking> bookings = bookingDao.findAll();
        
        for (Booking booking1 : bookings) {
            System.out.println("1) : " + booking1.getId().getIdEvent() + " == " + booking.getId().getIdEvent() + " 2) " + booking1.getId().getIdUser() + " == " +booking.getId().getIdUser());
            if((booking1.getId().getIdEvent() == booking.getEvent().getIdEvent()) && (booking1.getId().getIdUser() == booking.getUser().getIdUser()) ){
             
                    throw new TicketAlreadyBookedException("You have already booked your tickets for this event!");
            }
            
            
        }
    }

    private void checkIfEventPassed(Booking booking) {
        
        if(booking.getEvent().getDate().before(new Date())){
            System.out.println("EVENT TIME IS " + booking.getEvent().getDate());
            throw new EventPassedException("You can't book your tickets, because this event have already passed!");
        
        }
        
        
    }


    private void handleSeats(Booking booking) {
        System.out.println(booking.getEvent().getIdEvent());
        System.out.println(booking.getPaid());
       Event event = eventDao.findById(booking.getEvent().getIdEvent());
        System.out.println(event);
       TicketsCategory chosenCategory = null;
       
        for (TicketsCategory category : event.getTicketsCategory()) {
            
            if(category.getId().getIdTicketsCategory() == booking.getTicketsCategory().getId().getIdTicketsCategory() ){
                
                
                if(booking.getAmount() > (category.getMaxSeats() - category.getOccupiedSeats()) ){
                     
                    throw new AllSeatsTakenException("Unfortunely all seats for the chosen ticket category are taken ");
                
                }
                 
                chosenCategory = category;
                category.setOccupiedSeats(category.getOccupiedSeats() + booking.getAmount());
            
            }
            
        }
       
        if(chosenCategory == null){
         
              throw new CategoryNotFoundException("Category not found.");
        
        }
        
 
        eventDao.update(event);
        
          
        
    }

    private void freeSeats(long idEvent, long idUser) {
        
        Booking booking = bookingDao.findByCompositeId(idEvent, idUser);
        TicketsCategory chosenCategory = null;
        if(booking == null){
        
            throw new BookingNotFoundException("Event is not found !");
        
        }
        
        Event event = eventDao.findById(booking.getEvent().getIdEvent());
        
        if(event == null){
        
            throw new EventNotFoundException("Booking is not found !");
        }
     
        for (TicketsCategory category : event.getTicketsCategory()) {
            if(category.getId().getIdTicketsCategory() == booking.getTicketsCategory().getId().getIdTicketsCategory()){
            
                
                category.setOccupiedSeats(category.getOccupiedSeats() - booking.getAmount());
                chosenCategory = category;
            }
            
        }
        
        if(chosenCategory == null){
        
            throw new CategoryNotFoundException("Category not found!");
        
        }
        
        eventDao.update(event);
        
        
    }

    private Booking addUser(Booking booking) {
        User user = userRepository.findByName(booking.getUser().getName());
        System.out.println("USER JE user" + user.getRoles());
        
        booking.setUser(user);
        return booking;
    }

    private List<Booking> findByName(List<Booking> allBookings, String username) {
       List<Booking> myBookings = new ArrayList<>();
        for (Booking booking: allBookings) {
            
            if(booking.getUser().getName().equals(username)){
            
                myBookings.add(booking);
                
            }
            
        }
        return myBookings;
    }

    
    
    
    
}
