/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.ac.silab.BookingApi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Vladimir
 */
@javax.persistence.Entity
@Table

public class EventType implements rs.fon.ac.silab.BookingApi.domain.Entity{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idEventType;
    private String name;
    private String description;
    
   
    public EventType( String name, String description) {
        
        this.name = name;
        this.description = description;
    }

    public EventType() {
    }
    
    
    /**
     * @return the idEventType
     */
    public long getIdEventType() {
        return idEventType;
    }

    /**
     * @param idEventType the idEventType to set
     */
    public void setIdEventType(long idEventType) {
        this.idEventType = idEventType;
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
        return name;
    }
    
    
    

    
}

