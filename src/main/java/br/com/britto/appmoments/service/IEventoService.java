package br.com.britto.appmoments.service;

import br.com.britto.appmoments.dto.EventoDTO;
import br.com.britto.appmoments.model.Evento;

import java.util.List;

public interface IEventoService {

    public Evento create(Evento evento);
    public EventoDTO findByUuid(String uuid);
    public Evento update(Evento evento);
    public List<EventoDTO> findByCliente(Integer idCliente);

}
