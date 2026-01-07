package br.com.britto.appmoments.dto.foto;

import jakarta.validation.constraints.NotBlank;

public record FotoDTO(
        @NotBlank(message = "O UUID é obrigatório")
        String uuid,
        @NotBlank(message = "O conteúdo da foto em Base64 é obrigatório")
        String fotoBase64
) {
}
