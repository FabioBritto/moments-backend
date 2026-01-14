package br.com.britto.appmoments.security.config;

import br.com.britto.appmoments.security.service.AccessTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AppMomentsFilter extends OncePerRequestFilter {

    private final AccessTokenService accessTokenService;
    private final UserDetailsService userDetailsService;

    public AppMomentsFilter(AccessTokenService accessTokenService, UserDetailsService userDetailsService) {
        this.accessTokenService = accessTokenService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = this.getToken(request);
        if(token != null) {
            String login = accessTokenService.validateAccessToken(token);
            if(login != null && !login.isEmpty()) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(login);
                Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if(header == null || !header.startsWith("Bearer ")) return null;
        return header.replace("Bearer ", "");
    }
}
