package br.com.britto.appmoments.security.service;

import br.com.britto.appmoments.exception.ClienteNotFoundException;
import br.com.britto.appmoments.repository.ClienteRepository;
import br.com.britto.appmoments.security.authuser.AuthUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AuthServiceImpl implements IAuthService{

    private ClienteRepository repository;

    public AuthServiceImpl(ClienteRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new AuthUser(repository.findByEmail(username).orElseThrow(() -> new ClienteNotFoundException("Cliente não encontrado na base de dados")));
    }
}
