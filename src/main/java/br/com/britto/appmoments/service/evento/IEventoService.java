package br.com.britto.appmoments.service.evento;

import br.com.britto.appmoments.dto.evento.CreateEventoDTO;
import br.com.britto.appmoments.dto.evento.EventoDTO;
import br.com.britto.appmoments.model.Evento;

import java.util.List;

public interface IEventoService {

    public Evento create(CreateEventoDTO dto);
    public EventoDTO findByUuid(String uuid);
    public Evento update(Evento evento);
    public List<EventoDTO> findByCliente(Integer idCliente);

}
