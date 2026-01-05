package br.com.britto.appmoments.repository;

import br.com.britto.appmoments.dto.evento.EventoDTO;
import br.com.britto.appmoments.model.Cliente;
import br.com.britto.appmoments.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EventoRepository extends JpaRepository<Evento, Integer> {

    /*
    O HQL substitui nomenclatura de tabelas por nomenclatura de classes
                    nomenclatura de colunas por nomenclatura de atributos
     */

    @Query(" SELECT new br.com.britto.appmoments.dto.evento.EventoDTO(e.uuid, e.titulo, e.frase, e.dataHoraInicio, e.dataHoraFim, e.linkMoldura) " +
            " FROM Evento e WHERE e.cliente = :cliente")
    public List<EventoDTO> findByCliente(Cliente cliente);
    public Optional<Evento> findByUuid(String uuid);

}
