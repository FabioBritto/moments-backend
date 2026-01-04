package br.com.britto.appmoments.repository;

import br.com.britto.appmoments.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
