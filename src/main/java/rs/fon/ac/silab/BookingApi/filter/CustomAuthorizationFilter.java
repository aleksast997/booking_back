/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.ac.silab.BookingApi.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import static java.util.Arrays.stream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * @author Vladimir
 */
public class CustomAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       
        if(request.getServletPath().equals("/login")){
        
            filterChain.doFilter(request, response);
        
        }
        else{
        
            String authorizationHeader = request.getHeader(AUTHORIZATION);
            if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            
                
                try{
                    String token = authorizationHeader.substring("Bearer ".length());
                    Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                    JWTVerifier verifier = JWT.require(algorithm).build();
                    DecodedJWT decodedJWT = verifier.verify(token);
                    String username = decodedJWT.getSubject();
                    String[] roles = decodedJWT.getClaim("roles").asArray((String.class));
                    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    stream(roles).forEach(role ->{
                 
                    authorities.add(new SimpleGrantedAuthority(role));
                 
                 });
                 
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    
                filterChain.doFilter(request, response);
                 
                 
                }
                
                
                 catch(Exception exception){
                         
      
                     response.setHeader("error", exception.getMessage());
                     //response.sendError(HttpStatus.FORBIDDEN.value());
                      response.setStatus(HttpStatus.FORBIDDEN.value());
                     
                     Map<String, String> tokens = new HashMap<>();
                     tokens.put("error_message", exception.getMessage());
                        
                      response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        
                      new ObjectMapper().writeValue(response.getOutputStream(), tokens);
                     
                 
                }
                 
                 
                 
            }
            
            else{
            
                  filterChain.doFilter(request, response);
                
            
            }
            
            
            
            
            
            
        }
        
        
    }
    
    
    
}
