package br.com.britto.appmoments.service.cliente;

import br.com.britto.appmoments.dto.cliente.ClienteDTO;
import br.com.britto.appmoments.dto.cliente.UpdateClienteDTO;
import br.com.britto.appmoments.exception.AlreadyExistingUniqueData;
import br.com.britto.appmoments.exception.ClienteNotFoundException;
import br.com.britto.appmoments.model.Cliente;
import br.com.britto.appmoments.repository.ClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements IClienteService {

    private final ClienteRepository repository;

    public ClienteServiceImpl(ClienteRepository repository) {
        this.repository = repository;
    }

    @Override
    public ClienteDTO create(Cliente cliente) {
        if(repository.findByEmail(cliente.getEmail()).isPresent()) throw new AlreadyExistingUniqueData("Este email já está cadastrado no sistema");
        if(repository.findByTelefone(cliente.getTelefone()).isPresent()) throw new AlreadyExistingUniqueData("Este telefone já está cadastrado no sistema");
        Cliente created = repository.save(cliente);
        return ClienteDTO.fromEntity(created);
    }

    @Override
    public ClienteDTO update(UpdateClienteDTO clienteDTO) {
        Cliente cliente =  repository.findById(clienteDTO.id()).orElseThrow(() -> new ClienteNotFoundException("O cliente não existe na base de dados"));
        if(!cliente.getTelefone().equals(clienteDTO.telefone()) && repository.findByTelefone(clienteDTO.telefone()).isPresent()) {
            throw new AlreadyExistingUniqueData("Este telefone já está cadastrado no sistema");
        }
        cliente.setNome(clienteDTO.nome());
        cliente.setTelefone(clienteDTO.telefone());
        Cliente updated = repository.save(cliente);
        return ClienteDTO.fromEntity(updated);
    }

}
