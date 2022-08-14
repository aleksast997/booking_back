/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.ac.silab.BookingApi.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import rs.fon.ac.silab.BookingApi.domain.id.TicketCategoryId;
import javax.persistence.EmbeddedId;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

/**
 *
 * @author Vladimir
 */
@javax.persistence.Entity
@Table
public class TicketsCategory implements Entity{
    
    
    @EmbeddedId
    private TicketCategoryId id = new TicketCategoryId();
    
    
    @JsonBackReference
    @MapsId("idEvent")
    @JoinColumn(name = "idEvent")
    @ManyToOne
    private Event event;
    
    
    private String name;
    private String description;
    private int maxSeats;
    private int occupiedSeats;
    private double price;

    public TicketsCategory() {
    }

    public TicketsCategory(Event event, String name, String description, int maxSeats, int occupiedSeats, double price ) {
        this.event = event;
        this.name = name;
        this.description = description;
        this.maxSeats = maxSeats;
        this.occupiedSeats = occupiedSeats;
        this.price = price;
       
    }

    /**
     * @return the id
     */
    public TicketCategoryId getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(TicketCategoryId id) {
        this.id = id;
    }

    /**
     * @return the event
     */
    public Event getEvent() {
        return event;
    }

    /**
     * @param event the event to set
     */
    public void setEvent(Event event) {
        this.event = event;
    }

    /**
     * @return the idTicketsCategory
     */
   

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

    /**
     * @return the maxSeats
     */
    public int getMaxSeats() {
        return maxSeats;
    }

    /**
     * @param maxSeats the maxSeats to set
     */
    public void setMaxSeats(int maxSeats) {
        this.maxSeats = maxSeats;
    }

    /**
     * @return the occupiedSeats
     */
    public int getOccupiedSeats() {
        return occupiedSeats;
    }

    /**
     * @param occupiedSeats the occupiedSeats to set
     */
    public void setOccupiedSeats(int occupiedSeats) {
        this.occupiedSeats = occupiedSeats;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
      return name + " " + description + " " + id.getIdEvent() + "";
    }

    
    
    
    
    
    
}
