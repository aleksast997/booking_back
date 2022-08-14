/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.ac.silab.BookingApi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Vladimir
 */
@javax.persistence.Entity
@Table
public class Event implements rs.fon.ac.silab.BookingApi.domain.Entity{
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long idEvent;
    private String name;
    private String description;
    private Date date;
    
    
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "idEventType")
    private EventType eventType;
    
    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "idPlace")
    private Place place;
    
  
    @JsonManagedReference
    @OneToMany(mappedBy = "event" , cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<TicketsCategory> ticketsCategory = new ArrayList<>();
    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idUser")
    private User user;
   
    
    
    public Event() {
    }

    public Event( String name, String description, Date date, EventType eventType, List<Booking> bookings, List<TicketsCategory> ticketsCategory, User user) {
        this.ticketsCategory = ticketsCategory;
        this.name = name;
        this.description = description;
        this.date = date;
        this.eventType = eventType;
        this.user = user;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "[ EVENT " + name +" ID" + idEvent + "[ " + ticketsCategory + " ] ]";
    }

  

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the eventType
     */
    public EventType getEventType() {
        return eventType;
    }

    /**
     * @param eventType the eventType to set
     */
    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    /*
    public void addBooking(Booking booking){
        
        if(getBookings() == null){
        
            setBookings(new ArrayList<>());
            
        }
    
        getBookings().add(booking);
        booking.setEvent(this);
        
    }
    */
    public void addCategory(TicketsCategory ticketCategory){
        
        if(getTicketsCategory() == null){
        
            setTicketsCategory(new ArrayList<>());
            
        }
    
      this.ticketsCategory.add(ticketCategory);
      
        
    }
    
    
    /**
     * @return the idEvent
     */
    public Long getIdEvent() {
        return idEvent;
    }

    /**
     * @param idEvent the idEvent to set
     */
    public void setIdEvent(Long idEvent) {
        this.idEvent = idEvent;
    }

    /**
     * @return the bookings
     */
    /*
    public List<Booking> getBookings() {
        return bookings;
    }

    
    
    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
    */
    /**
     * @return the ticketsCategory
     */
    public List<TicketsCategory> getTicketsCategory() {
        return ticketsCategory;
    }

    /**
     * @param ticketsCategory the ticketsCategory to set
     */
    public void setTicketsCategory(List<TicketsCategory> ticketsCategory) {
        this.ticketsCategory = ticketsCategory;
    }

    /**
     * @return the place
     */
    public Place getPlace() {
        return place;
    }

    /**
     * @param place the place to set
     */
    public void setPlace(Place place) {
        this.place = place;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
     
   
    
    
}
