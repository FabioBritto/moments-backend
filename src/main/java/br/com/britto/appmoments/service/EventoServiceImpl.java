package br.com.britto.appmoments.service;

import br.com.britto.appmoments.dto.CreateEventoDTO;
import br.com.britto.appmoments.dto.EventoDTO;
import br.com.britto.appmoments.exception.*;
import br.com.britto.appmoments.model.Cliente;
import br.com.britto.appmoments.model.Evento;
import br.com.britto.appmoments.repository.ClienteRepository;
import br.com.britto.appmoments.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class EventoServiceImpl implements IEventoService {

    private final IFileStorageService storage;

    private EventoRepository repository;

    private ClienteRepository clienteRepository;

    public EventoServiceImpl(IFileStorageService storage, EventoRepository repository, ClienteRepository clienteRepository) {
        this.storage = storage;
        this.repository = repository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    @Transactional
    public Evento create(CreateEventoDTO dto) {
        Evento evento = dto.toEvento();
        evento.setUuid(UUID.randomUUID().toString());
        evento.setDataHoraFim(evento.getDataHoraInicio().plusHours(4)); //Evento sempre com 4hrs de duração
        storage.createAlbum(evento.getUuid());
        return repository.save(evento);
    }

    @Override
    public EventoDTO findByUuid(String uuid){
        Evento evento = repository.findByUuid(uuid).orElseThrow(() -> new UuidNotFoundException("UUID não encontrado"));
        return EventoDTO.fromEvento(evento);
    }

    @Override
    public Evento update(Evento evento) {
        return repository.save(evento);
    }

    @Override
    public List<EventoDTO> findByCliente(Integer idCliente) {
        Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(() -> new ClienteNotFoundException("Cliente não encontrado"));
        return repository.findByCliente(cliente);
    }
}
