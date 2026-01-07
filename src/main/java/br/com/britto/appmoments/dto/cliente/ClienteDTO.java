package br.com.britto.appmoments.dto.cliente;

import br.com.britto.appmoments.model.Cliente;

public record ClienteDTO(
        
        String nome,
        String email,
        String telefone
) {

    public static ClienteDTO fromEntity(Cliente cliente) {
        return new ClienteDTO(cliente.getNome(), cliente.getEmail(), cliente.getTelefone());
    }
}
