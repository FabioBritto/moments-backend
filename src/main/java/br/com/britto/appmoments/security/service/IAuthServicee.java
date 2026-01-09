package br.com.britto.appmoments.security.service;

import br.com.britto.appmoments.dto.login.LoginDTO;
import br.com.britto.appmoments.dto.login.LoginResponseDTO;

public interface IAuthServicee {

    public LoginResponseDTO login(LoginDTO loginDTO);
}
