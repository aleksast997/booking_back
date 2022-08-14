/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.ac.silab.BookingApi.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import rs.fon.ac.silab.BookingApi.exceptions.AllSeatsTakenException;
import rs.fon.ac.silab.BookingApi.exceptions.ApplicationErrorResponse;
import rs.fon.ac.silab.BookingApi.exceptions.ApplicationErrorResponse;
import rs.fon.ac.silab.BookingApi.exceptions.BookingNotFoundException;
import rs.fon.ac.silab.BookingApi.exceptions.CategoryNotFoundException;
import rs.fon.ac.silab.BookingApi.exceptions.EventNotFoundException;
import rs.fon.ac.silab.BookingApi.exceptions.EventPassedException;
import rs.fon.ac.silab.BookingApi.exceptions.TicketAlreadyBookedException;
import rs.fon.ac.silab.BookingApi.exceptions.UserNotFoundException;

/**
 *
 * @author Vladimir
 */
@ControllerAdvice 
public class ApplicationExceptionHandler {
   
    @ExceptionHandler
    public ResponseEntity<ApplicationErrorResponse> handleException(EventNotFoundException enfe){
           
        ApplicationErrorResponse error = new ApplicationErrorResponse();
           error.setStatus(HttpStatus.NOT_FOUND.value());
           error.setMessage(enfe.getMessage());
           error.setTimeStamp(System.currentTimeMillis());
          
           return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
            
    }
    
       
    @ExceptionHandler
    public ResponseEntity<ApplicationErrorResponse> handleException(Exception exception){
        
           ApplicationErrorResponse error = new ApplicationErrorResponse();
           error.setStatus(HttpStatus.BAD_REQUEST.value());
           error.setMessage(exception.getMessage());
           error.setTimeStamp(System.currentTimeMillis());
             
           return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
              
    }
    
     @ExceptionHandler
    public ResponseEntity<ApplicationErrorResponse> handleException(TicketAlreadyBookedException enfe){
           
           ApplicationErrorResponse error = new ApplicationErrorResponse();
           error.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
           error.setMessage(enfe.getMessage());
           error.setTimeStamp(System.currentTimeMillis());
          
           return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
            
        
            
    }
    
     @ExceptionHandler
    public ResponseEntity<ApplicationErrorResponse> handleException(EventPassedException enfe){
           
           ApplicationErrorResponse error = new ApplicationErrorResponse();
           error.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
           error.setMessage(enfe.getMessage());
           error.setTimeStamp(System.currentTimeMillis());
          
           return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
            
        
            
    }
    
    //AllSeats Taken -  Implementacija
    
    @ExceptionHandler
    public ResponseEntity<ApplicationErrorResponse> handleException(BookingNotFoundException enfe){
           
           ApplicationErrorResponse error = new ApplicationErrorResponse();
           error.setStatus(HttpStatus.NOT_FOUND.value());
           error.setMessage(enfe.getMessage());
           error.setTimeStamp(System.currentTimeMillis());
          
           return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
            
        
            
    }
    
    @ExceptionHandler
    public ResponseEntity<ApplicationErrorResponse> handleException(AllSeatsTakenException enfe){
           
           ApplicationErrorResponse error = new ApplicationErrorResponse();
           error.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
           error.setMessage(enfe.getMessage());
           error.setTimeStamp(System.currentTimeMillis());
          
           return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
            
        
            
    }
    
     @ExceptionHandler
    public ResponseEntity<ApplicationErrorResponse> handleException(CategoryNotFoundException enfe){
           
           ApplicationErrorResponse error = new ApplicationErrorResponse();
           error.setStatus(HttpStatus.NOT_FOUND.value());
           error.setMessage(enfe.getMessage());
           error.setTimeStamp(System.currentTimeMillis());
          
           return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
            
        
            
    }
    
    
    @ExceptionHandler
    public ResponseEntity<ApplicationErrorResponse> handleException(UserNotFoundException enfe){
           
           ApplicationErrorResponse error = new ApplicationErrorResponse();
           error.setStatus(HttpStatus.NOT_FOUND.value());
           error.setMessage(enfe.getMessage());
           error.setTimeStamp(System.currentTimeMillis());
          
           return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
            
        
            
    }
     
    
    
    
}
