package br.com.britto.appmoments.security.config;

import br.com.britto.appmoments.security.authuser.AuthUser;
import br.com.britto.appmoments.security.service.AuthServiceImpl;
import br.com.britto.appmoments.security.service.TokenUtilService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AppMomentsFilter extends OncePerRequestFilter {

    @Autowired
    private TokenUtilService tokenUtilService;

//    @Autowired
    private AuthServiceImpl authService;

    public AppMomentsFilter(TokenUtilService tokenUtilService, AuthServiceImpl authService) {
        this.tokenUtilService = tokenUtilService;
        this.authService = authService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = this.getToken(request);
        System.out.println("Token: " + token);
        if(token != null) {
            String login = tokenUtilService.validateLoginToken(token);
            System.out.println("Login: " +login);
            if(login != null && !login.isEmpty()) {
                UserDetails userDetails = authService.loadUserByUsername(login);
                System.out.println("User details: " + userDetails);
                Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

//        String token = this.getToken(request, response);
//        System.out.println("TOKEN: " + token);
//        System.out.println("REQUEST: " + request);
//        String login = tokenUtilService.generateLoginToken((AuthUser) authService.loadUserByUsername(login));
//        //String login = tokenUtilService.validateLoginToken(token);
//        // EU NÃO POSSO LANÇAR ERRO SE NÃO TEM TOKEN. SE NÃO TIVER, PASSARÁ PELO FILTRO, E SE ESTIVER NO /login, SERÁ CRIADO UM TOKEN PRA ESSE CARA
//
//        System.out.println("LOGIN: " + login);
//        if(login == null || login.isBlank()) {
//            System.out.println("LOGIN:" + login);
////            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Erro ao validar token");
//        } else {
//            UserDetails userDetails = authService.loadUserByUsername(login);
//            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        }

        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if(header == null) return null;
        return header.replace("Bearer ", "");


//        try {
//            String header = request.getHeader("Authorization");
//            if(header == null) {
//                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token ausente");
//            }
//            else {
//                return header.replace("Bearer ", "");
//            }
//        } catch (IOException e) {
//            throw new RuntimeException("Erro ao tentar recuperar token");
//        }
//        return null;
    }
}
