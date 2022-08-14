/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.ac.silab.BookingApi.service.impl;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.fon.ac.silab.BookingApi.dao.Dao;
import rs.fon.ac.silab.BookingApi.domain.Place;
import rs.fon.ac.silab.BookingApi.service.PlaceService;

/**
 *
 * @author Vladimir
 */
@Service
@Transactional
public class PlaceServiceImpl implements PlaceService{
    
    Dao<Place> placeDao;
    
    @Autowired
    public PlaceServiceImpl(Dao<Place> placeDao) {
        this.placeDao = placeDao;
    }
    
    
    @Override
    public List<Place> findAll() {
        return placeDao.findAll();
    }
    
}
