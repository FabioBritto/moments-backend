package br.com.britto.appmoments.controller;

import br.com.britto.appmoments.dto.evento.EventoDTO;
import br.com.britto.appmoments.dto.pagamento.PaymentLinkDTO;
import br.com.britto.appmoments.model.Evento;
import br.com.britto.appmoments.service.pagamento.IPagamentoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class PagamentoController {

    private final IPagamentoService service;

    public PagamentoController(IPagamentoService service) {
        this.service = service;
    }

    @PostMapping("/pagamento/{uuidEvento}")
    public ResponseEntity<PaymentLinkDTO> generatePaymenteLink(@RequestParam String uuidEvento) {
        return ResponseEntity.status(200).body(service.createPaymenteLink(uuidEvento));
    }
}
