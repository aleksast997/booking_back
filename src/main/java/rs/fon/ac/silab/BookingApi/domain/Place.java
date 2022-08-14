/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.ac.silab.BookingApi.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Vladimir
 */
@javax.persistence.Entity
@Table
public class Place implements Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPlace;
    private String name;
    private int maxCapacity;

    public Place() {
    }

    public Place(long idPlace, String name, int maxCapacity) {
        this.idPlace = idPlace;
        this.name = name;
        this.maxCapacity = maxCapacity;
    }

    /**
     * @return the idPlace
     */
    public long getIdPlace() {
        return idPlace;
    }

    /**
     * @param idPlace the idPlace to set
     */
    public void setIdPlace(long idPlace) {
        this.idPlace = idPlace;
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
     * @return the maxCapacity
     */
    public int getMaxCapacity() {
        return maxCapacity;
    }

    /**
     * @param maxCapacity the maxCapacity to set
     */
    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }
    
    
    
    
}
