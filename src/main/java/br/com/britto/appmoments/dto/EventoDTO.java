package br.com.britto.appmoments.dto;

import br.com.britto.appmoments.model.Evento;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record EventoDTO(

    String uuid,
    @NotBlank(message = "O título é obrigatório")
    String titulo,

    @NotBlank(message = "A frase é obrigatória")
    String frase,

    @NotBlank(message = "A data e hora de início do evento são obrigatórios")
    LocalDateTime inicio,
    LocalDateTime fim,

    @NotBlank(message = "A moldura deve ser escolhida")
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
