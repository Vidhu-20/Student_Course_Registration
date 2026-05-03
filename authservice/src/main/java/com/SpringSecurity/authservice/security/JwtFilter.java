package com.SpringSecurity.authservice.security;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.toLowerCase().startsWith("bearer ")) {

            String token = authHeader.substring(7).trim();

            if (token.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                response.getWriter().write("{\"error\": \"Authorization token is missing\"}");
                return;
            }

            try {
                // ✅ Step 1: Validate the token
                if (!jwtUtil.isTokenValid(token)) {
                    System.err.println("[JWT FILTER] Invalid or expired token");
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.setContentType("application/json");
                    response.getWriter().write("{\"error\": \"Invalid or expired token\"}");
                    return;
                }

                // ✅ Step 2: Extract user details from token
                String username = jwtUtil.extractUsername(token);
                String role = jwtUtil.extractRole(token);

                if (username != null && role != null) {
                    // Normalize role to uppercase
                    role = role.toUpperCase();
                    System.out.println("[JWT FILTER] Valid token for user: " + username + ", role: " + role);

                    // ✅ Step 3: Create authorities with ROLE_ prefix
                    List<SimpleGrantedAuthority> authorities = List.of(
                            new SimpleGrantedAuthority("ROLE_" + role)
                    );
                    System.out.println("[JWT FILTER] Authority set as: ROLE_" + role);

                    // ✅ Step 4: Create authentication token
                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(
                                    username,           // principal (username)
                                    null,               // credentials (not needed for JWT)
                                    authorities         // authorities/roles
                            );

                    // ✅ Step 5: Set authentication in SecurityContext
                    SecurityContextHolder.getContext().setAuthentication(auth);

                    System.out.println("[JWT FILTER] Authentication set in SecurityContext for: " + username);

                } else {
                    System.err.println("[JWT FILTER] Could not extract username or role from token");
                }

            } catch (Exception e) {
                System.err.println("[JWT FILTER] Token processing error: " + e.getMessage());
                SecurityContextHolder.clearContext();
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("{\"error\": \"Token validation failed\"}");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}