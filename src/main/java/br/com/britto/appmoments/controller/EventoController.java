package br.com.britto.appmoments.controller;

import br.com.britto.appmoments.dto.evento.CreateEventoDTO;
import br.com.britto.appmoments.dto.evento.EventoDTO;
import br.com.britto.appmoments.model.Evento;
import br.com.britto.appmoments.service.evento.IEventoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class EventoController {

    @Autowired
    private IEventoService service;

    @PostMapping("/eventos")
    public ResponseEntity<Evento> create(@RequestBody @Valid CreateEventoDTO evento) {
        return ResponseEntity.status(201).body(service.create(evento));
    }

    @GetMapping("/eventos/{uuid}")
    public ResponseEntity<EventoDTO> findByUuid(@PathVariable String uuid) {
        return ResponseEntity.status(HttpStatus.FOUND).body(service.findByUuid(uuid));
    }

    @PutMapping("/eventos")
    public ResponseEntity<Evento> update(@RequestBody Evento evento) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(evento));
    }

    @GetMapping("/eventos/cliente/{id}")
    public ResponseEntity<List<EventoDTO>> findByCliente(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(service.findByCliente(id));
    }
}
