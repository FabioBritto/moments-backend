package br.com.britto.appmoments.repository;

import br.com.britto.appmoments.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    public Optional<Cliente> findByEmail(String email);
    public Optional<Cliente> findByTelefone(String telefone);
}
