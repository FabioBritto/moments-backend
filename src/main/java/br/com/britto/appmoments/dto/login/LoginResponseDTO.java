package br.com.britto.appmoments.dto.login;

public record LoginResponseDTO(
        String accessToken,
        String refreshToken
) {
}
