/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.ac.silab.BookingApi.service;

import java.util.List;
import rs.fon.ac.silab.BookingApi.domain.EventType;

/**
 *
 * @author Vladimir
 */
public interface EventTypeService {
    List<EventType> findAll();
}
