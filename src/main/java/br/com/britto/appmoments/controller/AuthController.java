package br.com.britto.appmoments.controller;

import br.com.britto.appmoments.dto.login.LoginDTO;
import br.com.britto.appmoments.dto.login.LoginResponseDTO;
import br.com.britto.appmoments.dto.login.NewAccessTokenDTO;
import br.com.britto.appmoments.dto.login.RefreshTokenDTO;
import br.com.britto.appmoments.exception.TokenException;
import br.com.britto.appmoments.security.authuser.AuthUser;
import br.com.britto.appmoments.security.redis.RefreshTokenService;
import br.com.britto.appmoments.security.service.AccessTokenService;
import br.com.britto.appmoments.service.cliente.IClienteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final AccessTokenService accessTokenService;
    private final RefreshTokenService refreshTokenService;
    private final IClienteService clienteService;

    public AuthController(AuthenticationManager authenticationManager, AccessTokenService accessTokenService, RefreshTokenService refreshTokenService,
                          IClienteService clienteService) {
        this.authenticationManager = authenticationManager;
        this.accessTokenService = accessTokenService;
        this.refreshTokenService = refreshTokenService;
        this.clienteService = clienteService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginDTO loginDTO) {
        UsernamePasswordAuthenticationToken userPass = new UsernamePasswordAuthenticationToken(loginDTO.email(), loginDTO.senha());
        Authentication authentication = authenticationManager.authenticate(userPass);
        String accessToken = accessTokenService.generateAccessToken((AuthUser) authentication.getPrincipal());
        String refreshToken = refreshTokenService.create(((AuthUser) authentication.getPrincipal()).getUserCliente().getId());
        return ResponseEntity.status(200).body(new LoginResponseDTO(accessToken, refreshToken));
    }

    @PostMapping("/refresh")
    public ResponseEntity<NewAccessTokenDTO> refresh(@RequestBody RefreshTokenDTO refreshToken) {
        Integer userId = refreshTokenService.validate(refreshToken).orElseThrow(() -> new TokenException("Token inválido"));
        AuthUser authUser = new AuthUser(clienteService.findById(userId));
        NewAccessTokenDTO dto = new NewAccessTokenDTO(accessTokenService.generateAccessToken(authUser));
        return ResponseEntity.status(200).body(dto);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody RefreshTokenDTO refreshToken) {
        System.out.println("RefreshToken: " + refreshToken.refreshToken());
        refreshTokenService.revoke(refreshToken);
        return ResponseEntity.noContent().build();
    }
}
