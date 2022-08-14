/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.ac.silab.BookingApi.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import rs.fon.ac.silab.BookingApi.domain.User;

/**
 *
 * @author Vladimir
 */
public interface UserRepository extends JpaRepository<User, Long>{

    public void deleteUserByidUser(long idUser);
    public User findUserByidUser(long idUser);
    public User findByName(String name);
    
}
