package br.com.britto.appmoments.controller;

import br.com.britto.appmoments.dto.login.LoginDTO;
import br.com.britto.appmoments.dto.login.LoginResponseDTO;
import br.com.britto.appmoments.security.authuser.AuthUser;
import br.com.britto.appmoments.security.service.TokenUtilService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenUtilService tokenUtilService;

    public AuthController(AuthenticationManager authenticationManager, TokenUtilService tokenUtilService) {
        this.authenticationManager = authenticationManager;
        this.tokenUtilService = tokenUtilService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginDTO loginDTO) {
        UsernamePasswordAuthenticationToken userPass = new UsernamePasswordAuthenticationToken(loginDTO.email(), loginDTO.senha());
        Authentication authentication = authenticationManager.authenticate(userPass);
        LoginResponseDTO token = tokenUtilService.generateLoginToken((AuthUser) authentication.getPrincipal());
        return ResponseEntity.status(200).body(token);
    }
}
