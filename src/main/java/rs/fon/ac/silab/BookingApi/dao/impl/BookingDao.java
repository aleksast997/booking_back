/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.ac.silab.BookingApi.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import rs.fon.ac.silab.BookingApi.dao.Dao;
import rs.fon.ac.silab.BookingApi.domain.Booking;
import rs.fon.ac.silab.BookingApi.domain.Event;
import rs.fon.ac.silab.BookingApi.domain.TicketsCategory;
import rs.fon.ac.silab.BookingApi.domain.User;
import rs.fon.ac.silab.BookingApi.domain.id.BookingID;

/**
 *
 * @author Vladimir
 */
@Repository
public class BookingDao implements Dao<Booking>{

    private EntityManager entityManager;

    @Autowired
    public BookingDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    
    @Override
    public Booking save(Booking t) {
        
        
       Booking booking =  addId(t);
       entityManager.merge(booking);
      
        return t;
    }

    @Override
    public List<Booking> findAll() {
        return entityManager.createQuery("SELECT e FROM Booking e", Booking.class).getResultList();
    }

    @Override
    public void deleteById(long id)  {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Booking findById(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Booking update(Booking t){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
      
   

    @Override
    public void deleteByCompositeId(long idEvent, long idUser) {
        Booking booking = findBooking(idEvent, idUser);
      
      
        entityManager.remove(booking);
    }

    private Booking findBooking(long idEvent, long idUser) {
        BookingID  id = new BookingID(idUser, idEvent);
        return entityManager.find(Booking.class, id);
    }

    private Booking addId(Booking t) {
       t.setId(new BookingID(t.getUser().getIdUser(), t.getEvent().getIdEvent()));
        System.out.println("BLAAAAAAAaa6" + t.getTicketsCategory().getId());
       return t;
    }

    @Override
    public Booking findByCompositeId(long id1, long id2) {
       BookingID  id = new BookingID(id2, id1);
        return entityManager.find(Booking.class, id);
    }
    
    
}
