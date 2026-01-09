package br.com.britto.appmoments.security.authuser;

import br.com.britto.appmoments.model.Cliente;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class AuthUser implements UserDetails {

    private Cliente userCliente;

    public AuthUser(Cliente userCliente) {
        System.out.println("User cliente: " + userCliente);
        this.userCliente = userCliente;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return userCliente.getSenha();
    }

    @Override
    public String getUsername() {
        return userCliente.getNome();
    }

    public Cliente getUserCliente() {
        return userCliente;
    }
}
