package br.com.britto.appmoments.security.service;

import br.com.britto.appmoments.dto.login.LoginDTO;
import br.com.britto.appmoments.dto.login.LoginResponseDTO;
import br.com.britto.appmoments.exception.ClienteNotFoundException;
import br.com.britto.appmoments.exception.WrongLoginDataException;
import br.com.britto.appmoments.model.Cliente;
import br.com.britto.appmoments.repository.ClienteRepository;
import br.com.britto.appmoments.security.authuser.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements IAuthServicee, UserDetailsService {

    private ClienteRepository repository;

    public AuthServiceImpl(ClienteRepository repository) {
        this.repository = repository;
    }

    @Override
    public LoginResponseDTO login(LoginDTO loginDTO) {
//        try {
//            UsernamePasswordAuthenticationToken userPass = new UsernamePasswordAuthenticationToken(loginDTO.email(), loginDTO.senha());
//            Authentication auth = authenticationManager.authenticate(userPass);
//            LoginResponseDTO token = tokenUtilService.generateLoginToken((AuthUser) auth.getPrincipal());
//            return token;
//        } catch (Exception e) {
//            throw new WrongLoginDataException("Os dados de login estão incorretos");
//        }
        return null;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new AuthUser(repository.findByEmail(username).orElseThrow(() -> new ClienteNotFoundException("Cliente não encontrado na base de dados")));

    }
}
