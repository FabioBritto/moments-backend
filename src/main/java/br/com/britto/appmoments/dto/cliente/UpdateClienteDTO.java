package br.com.britto.appmoments.dto.cliente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateClienteDTO(
        @NotNull(message = "O ID do Cliente é obrigatório")
        Integer id,

        @NotBlank(message = "O nome do cliente é obrigatório")
        String nome,

        @NotBlank(message = "O telefone do cliente é obrigatório")
        String telefone
) {
}
