/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.ac.silab.BookingApi.domain.id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Vladimir
 */
@Embeddable
public class TicketCategoryId implements Serializable {
    private static long serialVersionUID = 1L;
    
 
    @Column(name = "idEvent")
    private long idEvent;
    
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTicketsCategory")
    private long idTicketsCategory;

    public TicketCategoryId() {
    }

    public TicketCategoryId(long idEvent, long idTicketsCategory) {
        this.idEvent = idEvent;
        this.idTicketsCategory = idTicketsCategory;
    }

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }

    /**
     * @return the idEvent
     */
    public long getIdEvent() {
        return idEvent;
    }

    /**
     * @param idEvent the idEvent to set
     */
    public void setIdEvent(long idEvent) {
        this.idEvent = idEvent;
    }

    /**
     * @return the idTicketsCateogry
     */
    public long getIdTicketsCategory() {
        return idTicketsCategory;
    }

    /**
     * @param idTicketsCateogry the idTicketsCateogry to set
     */
    public void setIdTicketsCategory(long idTicketsCategory) {
        this.idTicketsCategory = idTicketsCategory;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + (int) (this.idEvent ^ (this.idEvent >>> 32));
        hash = 79 * hash + (int) (this.idTicketsCategory ^ (this.idTicketsCategory >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TicketCategoryId other = (TicketCategoryId) obj;
        if (this.idEvent != other.idEvent) {
            return false;
        }
        if (this.idTicketsCategory != other.idTicketsCategory) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idEvent+ "" +idTicketsCategory;
    }
    
    
    
    
    
}
