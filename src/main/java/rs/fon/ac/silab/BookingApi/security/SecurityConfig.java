/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.ac.silab.BookingApi.security;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.security.auth.message.callback.PrivateKeyCallback;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import rs.fon.ac.silab.BookingApi.filter.CustomAuthenticationFilter;
import rs.fon.ac.silab.BookingApi.filter.CustomAuthorizationFilter;

/**
 *
 * @author Vladimir
 */
@Configuration
@EnableWebSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private  UserDetailsService userDetailsService;
    
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
       http.csrf().disable();
      
       
       CorsConfiguration corsConfig = new CorsConfiguration().applyPermitDefaultValues();
        corsConfig.addAllowedMethod(HttpMethod.DELETE);
        corsConfig.addAllowedMethod(HttpMethod.PUT);
       http.cors().configurationSource(request-> corsConfig);
       
       http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
       
       http.authorizeHttpRequests().antMatchers("/user").permitAll();
       http.authorizeRequests().antMatchers("/organizator/**").hasAnyAuthority("organizer");
       http.authorizeRequests().
               antMatchers(HttpMethod.POST,"/event").hasAnyAuthority("organizer").
               antMatchers(HttpMethod.PUT,"/event").hasAnyAuthority("organizer").
               antMatchers(HttpMethod.DELETE,"/event").hasAnyAuthority("organizer").
               antMatchers(HttpMethod.GET, "/event").permitAll()
               .antMatchers("/booking/**").hasAnyAuthority("customer")
               .anyRequest().authenticated();
      
      
       http.addFilter(customAuthenticationFilter);
       http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
       http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));
       
       
       
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
         
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
       
    }
    
    @Bean
    public PasswordEncoder passwordEncoder(){
            
                    
            return new BCryptPasswordEncoder();
        
    }
    
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
    
         return super.authenticationManagerBean();
        
    }
    
    
    
    
}
