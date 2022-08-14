/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.ac.silab.BookingApi.service;

import java.util.List;
import rs.fon.ac.silab.BookingApi.domain.Role;
import rs.fon.ac.silab.BookingApi.domain.User;

/**
 *
 * @author Vladimir
 */

public interface UserService {

    public List<User> findAll();
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String name, String roleName);
    User getUser(String name);
    User addUser(User user);
     void deleteById(long id);
     public User findById(long id);
    //public User findById(long idUser);
    
    
}
