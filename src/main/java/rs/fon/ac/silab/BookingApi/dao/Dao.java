/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.ac.silab.BookingApi.dao;

import java.util.List;


/**
 *
 * @author Vladimir
 */
import rs.fon.ac.silab.BookingApi.domain.Entity;
public interface Dao <T> extends Entity{

    T save(T t) ;
    List<T> findAll();
    void deleteById(long id);
    T findById(long id);
    T update(T t);
    void deleteByCompositeId(long id1, long id2);
    T findByCompositeId(long id1, long id2);
    
    
}
