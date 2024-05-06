package com.school.school.security.Config;

import com.school.school.security.Entity.Jwt;
import com.school.school.security.Entity.Utilisateur;
import com.school.school.security.Service.UtilisateurService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.Objects;

@Service
public class JwtFilter extends OncePerRequestFilter {
    private HandlerExceptionResolver handlerExceptionResolver;
    private  UtilisateurService utilisateurService;
    private  JwtService jwtService;

    public JwtFilter(HandlerExceptionResolver handlerExceptionResolver, UtilisateurService utilisateurService , JwtService jwtService) {
        this.handlerExceptionResolver = handlerExceptionResolver;
        this.utilisateurService = utilisateurService;
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token ;
        Jwt tokenDansLaBDD = null;
        String username = null;
        boolean isTokenExpired = true;

        try {
        //Bearer eyJhbGciOiJIUzI1NiJ9.eyJub20iOiJCYW1iYSBPdXNtYW5lIiwiZW1haWwiOiJvYjczNTI0ODBAZ21haWwuY29tIn0.87lPeEdzKhECrhCoxqUNypgsfOx43zlol1JHKIqb3BA

        final String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Bearer ")){
            token = authorization.substring(7);
            tokenDansLaBDD = this.jwtService.tokenByValue(token);
         isTokenExpired =   jwtService.isTokenExpired(token);
            username = jwtService.extractUsername(token);
        }

        if (
                !isTokenExpired
                && Objects.equals(tokenDansLaBDD.getUtilisateur().getEmail(), username)
                && SecurityContextHolder.getContext().getAuthentication() == null){
          UserDetails userDetails = utilisateurService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken( userDetails, null , userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(request,response);
        }catch (final Exception exception){
            handlerExceptionResolver.resolveException(request,response,null,exception);

        }

    }
}
