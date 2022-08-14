/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.ac.silab.BookingApi.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rs.fon.ac.silab.BookingApi.dao.Dao;
import rs.fon.ac.silab.BookingApi.domain.EventType;
import rs.fon.ac.silab.BookingApi.domain.Role;
import rs.fon.ac.silab.BookingApi.domain.User;
import rs.fon.ac.silab.BookingApi.exceptions.UserNotFoundException;
import rs.fon.ac.silab.BookingApi.repository.RoleRepository;
import rs.fon.ac.silab.BookingApi.repository.UserRepository;
import rs.fon.ac.silab.BookingApi.service.UserService;

/**
 *
 * @author Vladimir
 */
@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService{
      private final UserRepository userRepository;
      private final RoleRepository roleRepository;
      private final PasswordEncoder passwordEncoder;
    
    @Autowired
    public UserServiceImpl( UserRepository userRepository, RoleRepository roleRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
       this.passwordEncoder = passwordEncoder;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username);
        if(user==null){
        
            throw new UserNotFoundException("User not found in the database");
        }
        
        
       
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role ->{authorities.add(new SimpleGrantedAuthority(role.getName()));});
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), authorities);
        
        
    }
    
    @Override
    public User addUser(User user){
    
        return userRepository.save(user);
    
    }
    
   
    @Override
    public User saveUser(User user){
        
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    
    
    }
 
    @Override
    public void deleteById(long id){
    
       userRepository.deleteUserByidUser(id);
    
    }
    
    @Override
    public User findById(long id){
        return null;
        //return userRepository.findUserById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
    
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Role saveRole(Role role) {
       return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String name, String roleName) {
        User user = userRepository.findByName(name);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
    
}
