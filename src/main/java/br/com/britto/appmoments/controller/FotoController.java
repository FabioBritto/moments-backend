package br.com.britto.appmoments.controller;

import br.com.britto.appmoments.dto.foto.FotoDTO;
import br.com.britto.appmoments.dto.foto.FotoResponseDTO;
import br.com.britto.appmoments.service.foto.IFotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class FotoController {

    @Autowired
    private IFotoService service;

    @PostMapping("/foto")
    public ResponseEntity<FotoResponseDTO> registerFoto(@RequestBody FotoDTO fotoDTO) {
        return ResponseEntity.status(201).body(service.sendFoto(fotoDTO));
    }
}
