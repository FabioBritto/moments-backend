package br.com.britto.appmoments.controller;

import br.com.britto.appmoments.dto.cliente.ClienteDTO;
import br.com.britto.appmoments.dto.cliente.UpdateClienteDTO;
import br.com.britto.appmoments.model.Cliente;
import br.com.britto.appmoments.service.cliente.IClienteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class ClienteController {

    private final IClienteService service;

    public ClienteController(IClienteService service) {
        this.service = service;
    }

    @PostMapping("/clientes")
    public ResponseEntity<ClienteDTO> create(@RequestBody Cliente cliente) {
        return ResponseEntity.status(201).body(service.create(cliente));
    }

    @PutMapping("/clientes")
    public ResponseEntity<ClienteDTO> update(@RequestBody @Valid UpdateClienteDTO clienteDTO) {
        return ResponseEntity.status(200).body(service.update(clienteDTO));
    }

}
