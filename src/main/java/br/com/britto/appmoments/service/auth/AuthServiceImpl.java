package br.com.britto.appmoments.service.auth;

import br.com.britto.appmoments.dto.login.LoginDTO;
import br.com.britto.appmoments.dto.login.LoginResponseDTO;
import br.com.britto.appmoments.exception.AlreadyExistingUniqueData;
import br.com.britto.appmoments.exception.ClienteNotFoundException;
import br.com.britto.appmoments.exception.WrongLoginDataException;
import br.com.britto.appmoments.model.Cliente;
import br.com.britto.appmoments.repository.ClienteRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements IAuthServicee {

    private ClienteRepository repository;

    public AuthServiceImpl(ClienteRepository repository) {
        this.repository = repository;
    }

    @Override
    public LoginResponseDTO login(LoginDTO loginDTO) {
        Cliente cliente =  repository.findByEmail(loginDTO.email()).orElseThrow(() -> new ClienteNotFoundException("O cliente não existe na base de dados"));
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(!encoder.matches(cliente.getSenha(), loginDTO.senha())) throw new WrongLoginDataException("Os dados de login estão incorretos");
        String token = "TOKEN PROVISÓRIO";
        return new LoginResponseDTO(token);
    }
}
