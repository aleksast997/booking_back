/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.ac.silab.BookingApi.service.impl;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import rs.fon.ac.silab.BookingApi.dao.Dao;
import rs.fon.ac.silab.BookingApi.domain.Event;
import rs.fon.ac.silab.BookingApi.domain.EventType;
import rs.fon.ac.silab.BookingApi.service.EventTypeService;

/**
 *
 * @author Vladimir
 */
@Service
@Transactional
public class EventTypeServiceImpl implements EventTypeService {
    private Dao<EventType> eventTypeDao;
   // private EventMapper eventMapper;
    //@Qualifier(value = "EventDaoSpringJPA")
    
    @Autowired
    public EventTypeServiceImpl( Dao<EventType> eventTypeDao) {
        this.eventTypeDao = eventTypeDao;
       // this.eventMapper = eventMapper;
    }
    @Override
    public List<EventType> findAll() {
       return eventTypeDao.findAll();
    }
    
}
