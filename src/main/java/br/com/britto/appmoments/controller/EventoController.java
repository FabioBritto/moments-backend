package br.com.britto.appmoments.controller;

import br.com.britto.appmoments.dto.EventoDTO;
import br.com.britto.appmoments.model.Evento;
import br.com.britto.appmoments.service.IEventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class EventoController {

    @Autowired
    private IEventoService service;

    @PostMapping("/eventos")
    public ResponseEntity<Evento> create(@RequestBody Evento evento) {
        Evento resultado = service.create(evento);
        if(resultado != null) return ResponseEntity.status(201).body(resultado);
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/eventos/{uuid}")
    public ResponseEntity<EventoDTO> findByUuid(@PathVariable String uuid) {
        EventoDTO dto = service.findByUuid(uuid);
        if(dto != null) return ResponseEntity.status(200).body(dto);
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/eventos/{uuid}")
    public ResponseEntity<Evento> update(@RequestBody Evento evento) {
        Evento resultado = service.update(evento);
        if(resultado != null) return ResponseEntity.status(200).body(resultado);
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/eventos/cliente/{id}")
    public ResponseEntity<List<EventoDTO>> findByCliente(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findByCliente(id));
    }
}
