package br.com.britto.appmoments.dto.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateClienteDTO(
        @NotBlank(message = "O email é obrigatório")
        @Email(message = "Formato de email inválido")
        String email,

        @NotBlank(message = "O número de telefone é obrigatório")
        String telefone,

        @NotBlank(message = "A senha é obrigatória")
        String senha
) {
}
