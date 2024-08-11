package br.unitins.service.implementations;

import br.unitins.dto.AuthDTO;
import br.unitins.dto.RegisterDTO;
import br.unitins.model.User;
import br.unitins.model.enums.Role;
import br.unitins.repository.UserRepository;
import br.unitins.service.interfaces.AuthService;
import br.unitins.service.utils.PasswordService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class AuthServiceImpl implements AuthService {
    @Inject
    private UserRepository repository;

    @Inject
    private PasswordService pService;

    @Override
    public User login(AuthDTO user) {
        String hash = pService.getHash(user.getPassword());

        return repository.findByEmailAndPassword(user.getEmail(), hash);
    }

    @Override
    public User register(RegisterDTO user) {
        User validUser;
        String hash = pService.getHash(user.getPassword());

        if (user.getType() == Role.THERAPIST.getId()) {
            validUser = user.toTherapist(hash);
        } else if (user.getType() == Role.FAMILY.getId()) {
            validUser = user.toFamily(hash);
        } else {
            throw new NotFoundException();
        }

        return repository.create(validUser);
    }

}
