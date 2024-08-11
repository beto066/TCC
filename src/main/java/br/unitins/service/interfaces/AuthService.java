package br.unitins.service.interfaces;

import br.unitins.dto.AuthDTO;
import br.unitins.dto.RegisterDTO;
import br.unitins.model.User;

public interface AuthService {
    User login(AuthDTO user);

    User register(RegisterDTO user);
}
