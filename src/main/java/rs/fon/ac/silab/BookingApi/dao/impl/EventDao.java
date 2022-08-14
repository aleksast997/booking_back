package rs.fon.ac.silab.BookingApi.dao.impl;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import rs.fon.ac.silab.BookingApi.dao.Dao;
import rs.fon.ac.silab.BookingApi.domain.Event;
import rs.fon.ac.silab.BookingApi.domain.EventType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Vladimir
 */
@Repository()
public class EventDao implements Dao<Event>{
    
    
    private EntityManager entityManager;

    public EventDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    

    @Override
    public Event save(Event event) {
        
        
        entityManager.persist(event);
        return event;
                
    }

    @Override
    public List<Event> findAll()  {
        return entityManager.createQuery("SELECT e FROM Event e ORDER BY e.name",Event.class).getResultList();
        
    }

    @Override
    public void deleteById(long id)  {
        Event event = entityManager.find(Event.class, id);
        entityManager.remove(event);
    }
    
    @PostConstruct
    public void test(){
        
        System.out.println("Init method of dao spring jpa");
           
    }

    

    @Override
    public Event update(Event event) {
        
        return entityManager.merge(event);
    }

    @Override
    public Event findById(long id) {
        return entityManager.find(Event.class,id);
    }

    @Override
    public void deleteByCompositeId(long id1, long id2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Event findByCompositeId(long id1, long id2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
