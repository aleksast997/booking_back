/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.ac.silab.BookingApi.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.fon.ac.silab.BookingApi.domain.Place;
import rs.fon.ac.silab.BookingApi.service.PlaceService;

/**
 *
 * @author Vladimir
 */
@RequestMapping(path = "/place")
@RestController
@ControllerAdvice

public class PlaceController {
    
    private PlaceService placeService;

    @Autowired
    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    
    @GetMapping
    @CrossOrigin
    ResponseEntity<List<Place>> findAll(){
    
        return ResponseEntity.ok().body(placeService.findAll());
    }
    
    
    
}
