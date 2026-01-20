package br.com.britto.appmoments.controller;

import br.com.britto.appmoments.dto.pagamento.PaymentLinkDTO;
import br.com.britto.appmoments.model.Evento;
import br.com.britto.appmoments.service.pagamento.IPagamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class PagamentoController {

    private final IPagamentoService service;

    public PagamentoController(IPagamentoService service) {
        this.service = service;
    }

    @PostMapping("/pagamento")
    public ResponseEntity<PaymentLinkDTO> generatePaymenteLink(@RequestBody Evento evento) {
        return ResponseEntity.status(200).body(service.createPaymenteLink(evento));
    }
}
