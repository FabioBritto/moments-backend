package br.com.britto.appmoments.service.cliente;

import br.com.britto.appmoments.dto.cliente.ClienteDTO;
import br.com.britto.appmoments.dto.cliente.CreateClienteDTO;
import br.com.britto.appmoments.dto.cliente.UpdateClienteDTO;
import br.com.britto.appmoments.model.Cliente;

import java.util.Optional;

public interface IClienteService {
    public ClienteDTO create(CreateClienteDTO clienteDTO);
    public ClienteDTO update(UpdateClienteDTO clienteDTO);
    public Cliente findById(Integer id);
}
