package br.unitins.service.implementations;

import java.io.IOException;
import java.util.List;

import br.unitins.dto.UserResponseDTO;
import br.unitins.form.UserForm;
import br.unitins.model.User;
import br.unitins.repository.UserRepository;
import br.unitins.service.interfaces.UserService;
import br.unitins.service.utils.EmailService;
import br.unitins.service.utils.FileService;
import br.unitins.service.utils.PasswordService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class UserServiceImpl implements UserService {
    @Inject
    private UserRepository userRepository;

    @Inject
    private FileService fileService;

    @Inject
    private PasswordService passwordService;

    @Override
    public List<UserResponseDTO> getAll() {
        return userRepository.findAllUsers();
    }

    @Override
    public List<UserResponseDTO> getListUser(String search) {
        return userRepository.findByName(search);
    }

    @Override
    public UserResponseDTO find(Long id) {
        return new UserResponseDTO(userRepository.findById(id));
    }

    @Override
    public UserResponseDTO update(UserForm form, Long userId) throws WebApplicationException {
        String imageName = "";

        if (
            form.getImageName() != null &&
            !form.getImageName().isEmpty() &&
            form.getImagem() != null &&
            form.getImagem().length > 0
        ) {
            try {
                imageName = fileService.saveUserImage(form.getImagem(), form.getImageName());
            } catch (IOException e) {
                Response.ok(e.getMessage(), MediaType.TEXT_PLAIN).status(422).build();
                e.printStackTrace();
            }
        }

        User entity = userRepository.findById(userId);

        if (form.getName() != null) {
            if (form.getName().trim().length() < 4 || form.getName().trim().length() > 30) {
                throw new WebApplicationException(
                    "the field name must be greater than 4 and less than 30",
                    422
                );
            }
            entity.name = form.getName();
        }
        if (form.getEmail() != null) {
            if (!EmailService.isEmail(form.getEmail())) {
                throw new WebApplicationException(
                    "the field email must be an email",
                    422
                );
            }
            entity.email = form.getEmail();
        }
        if (form.getPassword() != null && form.getPassword().trim().length() >= 6) {
            if (form.getPassword().trim().length() < 6) {
                throw new WebApplicationException(
                    "the field password must be greater than 4",
                    422
                );
            }
            entity.password = passwordService.getHash(form.getPassword());
        }
        if (imageName != null && !imageName.isEmpty()) {
            entity.imageName = imageName;
        }
        return new UserResponseDTO(entity);
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        User entity = userRepository.findById(id);
        if (entity == null){
            throw new NotFoundException();
        }

        userRepository.delete(entity);
    }

    @Override
    public Long count() {
        return userRepository.count();
    }
}
