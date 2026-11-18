package com.example.MyLittleProject.security;

import com.example.MyLittleProject.repository.AppUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// intercepts every incoming request checks for jwt in auth header n tells security who user is
@Component
@RequiredArgsConstructor
 public class JwtAuthenticationFilter extends OncePerRequestFilter {
// OncePerRequestFilter helper that checks jwt once per http request before reaching controller

    private final AppUserRepository userRepository;
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

       // read auth header from request
       String authHeader = request.getHeader("Authorization");

       // if theres no token let request continue
       if(authHeader == null || !authHeader.startsWith("Bearer ")) {
          filterChain.doFilter(request, response);
          return;
       }
   // cuts for Bearer n gets the the raw jwt token
       String token = authHeader.substring(7);
       String username = jwtService.extractUsername(token);

       if(username != null && SecurityContextHolder.getContext().getAuthentication() == null)
       {
          UserDetails userDetails = userRepository.findByUsername(username).orElse(null);

          if(userDetails != null && jwtService.isTokenValid(token, userDetails)) {

             UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
             authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
             SecurityContextHolder.getContext().setAuthentication(authToken);

          }

       }
       filterChain.doFilter(request, response);
    }

}
