package br.com.britto.appmoments.service.cliente;

import br.com.britto.appmoments.dto.cliente.ClienteDTO;
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
    public ClienteDTO update(Cliente cliente) {
        repository.findByEmail(cliente.getEmail()).orElseThrow(() -> new ClienteNotFoundException("O cliente não existe na base de dados"));
        repository.findByTelefone(cliente.getTelefone()).orElseThrow(() -> new AlreadyExistingUniqueData("Este telefone já está cadastrado no sistema"));
        Cliente updated = repository.save(cliente);
        return ClienteDTO.fromEntity(updated);
    }

}
