package br.com.britto.appmoments.controller;

import br.com.britto.appmoments.dto.evento.CreateEventoDTO;
import br.com.britto.appmoments.dto.evento.EventoDTO;
import br.com.britto.appmoments.model.Evento;
import br.com.britto.appmoments.service.evento.IEventoService;
import br.com.britto.appmoments.service.pagamento.IPagamentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class EventoController {

    private final IEventoService service;

    public EventoController(IEventoService service) {
        this.service = service;
    }

    /*
    Talvez eu precise desacoplar a criação do evento com a geração do link de pagamento. Eu não posso conseguir
    criar o evento, e ao dar erro na geração do link, retornar uma exceção. São dois processos distintos
     */
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

//    @PostMapping("/eventos/pagamento")
//    public ResponseEntity<Evento> pagamento(@RequestBody @Valid PagamentoRequestDTO pagamentoDTO) {
//        return ResponseEntity.status(200).body(pagamentoService.payment(pagamentoDTO));
//    }
}
