package br.com.britto.appmoments.dto;

import br.com.britto.appmoments.model.Evento;

import java.time.LocalDateTime;

public record EventoDTO(
    String uuid,
    String titulo,
    String frase,
    LocalDateTime inicio,
    LocalDateTime fim,
    String moldura
) {

    public Evento toEvento() {
        Evento evento = new Evento();
        evento.setUuid(uuid);
        evento.setTitulo(titulo);
        evento.setFrase(frase);
        evento.setDataHoraInicio(inicio);
        evento.setDataHoraFim(fim);
        evento.setLinkMoldura(moldura);
        return evento;
    }

}
