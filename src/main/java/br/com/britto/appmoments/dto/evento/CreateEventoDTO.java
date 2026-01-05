package br.com.britto.appmoments.dto.evento;

import br.com.britto.appmoments.model.Cliente;
import br.com.britto.appmoments.model.Evento;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record CreateEventoDTO(

        @NotBlank(message = "O título é obrigatório")
        @Size(min = 1, max = 100, message = "A frase deve ter ao menos 1 e no máximo 100 caracteres")
        String titulo,

        @NotBlank(message = "A frase é obrigatória")
        @Size(min = 1, max = 100, message = "A frase deve ter ao menos 1 e no máximo 100 caracteres")
        String frase,

        @Future
        LocalDateTime inicio,

        @NotBlank(message = "A moldura deve ser escolhida")
        String moldura,

        @NotNull
        Integer idCliente
) {

    public Evento toEvento() {
        Evento evento = new Evento();
        evento.setTitulo(titulo);
        evento.setFrase(frase);
        evento.setDataHoraInicio(inicio);
        evento.setLinkMoldura(moldura);
        return evento;
    }
}
