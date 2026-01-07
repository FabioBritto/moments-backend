package br.com.britto.appmoments.service.auth;

import br.com.britto.appmoments.dto.login.LoginDTO;
import br.com.britto.appmoments.dto.login.LoginResponseDTO;

public interface IAuthServicee {

    public LoginResponseDTO login(LoginDTO loginDTO);
}
