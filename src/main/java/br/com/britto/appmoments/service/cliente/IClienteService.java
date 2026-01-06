package br.com.britto.appmoments.service.cliente;

import br.com.britto.appmoments.dto.cliente.ClienteDTO;
import br.com.britto.appmoments.model.Cliente;

public interface IClienteService {
    public ClienteDTO create(Cliente cliente);
    public ClienteDTO update(Cliente cliente);
}
