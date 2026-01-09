package br.com.britto.appmoments.controller;

import br.com.britto.appmoments.dto.login.LoginDTO;
import br.com.britto.appmoments.dto.login.LoginResponseDTO;
import br.com.britto.appmoments.security.authuser.AuthUser;
import br.com.britto.appmoments.security.service.IAuthServicee;
import br.com.britto.appmoments.security.service.TokenUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

//    @Autowired
    private IAuthServicee service;
//
    @Autowired
    private AuthenticationManager authenticationManager;
//
//    @Autowired
    private TokenUtilService tokenUtilService;

    public AuthController(IAuthServicee service, AuthenticationManager authenticationManager, TokenUtilService tokenUtilService) {
        this.service = service;
        this.authenticationManager = authenticationManager;
        this.tokenUtilService = tokenUtilService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        System.out.println("CONTROLLER -> Email: " + loginDTO.email());
        System.out.println("CONTROLLER -> Senha: " + loginDTO.senha());
        UsernamePasswordAuthenticationToken userPass = new UsernamePasswordAuthenticationToken(loginDTO.email(), loginDTO.senha());
        System.out.println("CONTROLLER -> UserPass: " + userPass);
        Authentication authentication = authenticationManager.authenticate(userPass);
        System.out.println("Antes de gerar o token: " + authentication);
        LoginResponseDTO token = tokenUtilService.generateLoginToken((AuthUser) authentication.getPrincipal());
        System.out.println("Depois de gerar o token: " + token);
        return ResponseEntity.status(200).body(token);
    }
}
