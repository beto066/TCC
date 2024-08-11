package br.unitins.service.interfaces;

import java.util.List;

import br.unitins.dto.UserResponseDTO;
import br.unitins.form.UserForm;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.WebApplicationException;

public interface UserService {
    List<UserResponseDTO> getAll();

    List<UserResponseDTO> getListUser(String search);

    UserResponseDTO find(Long id);

    UserResponseDTO update(UserForm form, Long userId) throws WebApplicationException;

    void delete(Long id) throws NotFoundException;

    Long count();
}
