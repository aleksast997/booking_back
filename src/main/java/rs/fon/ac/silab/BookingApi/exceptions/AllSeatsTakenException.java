/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.ac.silab.BookingApi.exceptions;

/**
 *
 * @author Vladimir
 */
public class AllSeatsTakenException extends RuntimeException {

    public AllSeatsTakenException(String message) {
        
        super(message);
    }
    
    
}
