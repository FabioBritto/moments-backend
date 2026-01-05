package br.com.britto.appmoments.dto.evento;

import br.com.britto.appmoments.model.Evento;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record EventoDTO(
    @Size(min = 36, max = 36, message = "UUID inválido")
    @NotBlank(message = "O UUID é obrigatório")
    String uuid,

    @NotBlank(message = "O título é obrigatório")
    String titulo,

    @NotBlank(message = "A frase é obrigatória")
    String frase,

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

    public static EventoDTO fromEvento(Evento evento) {
        return new EventoDTO(evento.getUuid(), evento.getTitulo(), evento.getFrase(), evento.getDataHoraInicio(),
                evento.getDataHoraFim(), evento.getLinkMoldura());
    }

}
