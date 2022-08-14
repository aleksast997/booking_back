/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.ac.silab.BookingApi.domain.id;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Vladimir
 */
@Embeddable
public class BookingID implements Serializable {
     private static final long serialVersionUID = 1L;
     
    @Column(name = "idUser")
    private long idUser;
    
    @Column(name = "idEvent")
    private long idEvent;

    public BookingID() {
    }

    public BookingID(long idUser, long idEvent) {
        this.idUser = idUser;
        this.idEvent = idEvent;
    }

    /**
     * @return the idUser
     */
    public long getIdUser() {
        return idUser;
    }

    /**
     * @param idUser the idUser to set
     */
    public void setIdUser(long idUser) {
        this.idUser = idUser;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (int) (this.idUser ^ (this.idUser >>> 32));
        hash = 23 * hash + (int) (this.idEvent ^ (this.idEvent >>> 32));
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
        final BookingID other = (BookingID) obj;
        if (this.idUser != other.idUser) {
            return false;
        }
        if (this.idEvent != other.idEvent) {
            return false;
        }
        return true;
    }
    
    
    
}
