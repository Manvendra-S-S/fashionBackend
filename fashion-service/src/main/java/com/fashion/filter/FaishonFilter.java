package com.fashion.filter;

import com.fashion.exception.JWTTokenException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpMethod;

import java.io.IOException;
import io.jsonwebtoken.*;

public class FaishonFilter  extends GenericFilter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException, JWTTokenException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Request-Headers", "");


        if (request.getMethod().equals(HttpMethod.OPTIONS.name())) {
            filterChain.doFilter(request, response);
        } else {
            String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                throw new JWTTokenException("Missing or Invalid JWT Token");
            }
            String token = authHeader.substring(7);
            try {
                Claims claims = Jwts.parser().setSigningKey("secret").parseClaimsJws(token).getBody();
                HttpSession session = request.getSession();
                session.setAttribute("username", claims.getSubject());
            } catch (SignatureException e) {
                throw new JWTTokenException("Invalid JWT signature");
            } catch (MalformedJwtException e) {
                throw new JWTTokenException("Invalid JWT token");
            }
            filterChain.doFilter(request, response);
        }
    }
}
