/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.ac.silab.BookingApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.fon.ac.silab.BookingApi.domain.Role;

/**
 *
 * @author Vladimir
 */
public interface RoleRepository extends JpaRepository<Role, Long>{
    
    Role findByName(String name);
    
}
