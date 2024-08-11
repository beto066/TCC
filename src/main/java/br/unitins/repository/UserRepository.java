package br.unitins.repository;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.enterprise.context.ApplicationScoped;

import br.unitins.dto.UserResponseDTO;
import br.unitins.model.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User>{
    public  List<UserResponseDTO> findAllUsers() {
        return listAll().stream()
            .map(user -> new UserResponseDTO(user))
            .collect(Collectors.toList());
    }

    public  List<User> listAllUsers() {
        return listAll();
    }

    public  List<UserResponseDTO> findByName(String name) {
        return find("name LIKE ?1", "%"+name+"%").stream()
            .map(user -> new UserResponseDTO(user))
            .collect(Collectors.toList());
    }

    public  User findByEmailAndPassword(String email, String password) {
        try {
            return find("email = ?1 AND password = ?2", email, password).singleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public User create(User user) {
        this.persist(user);
        return user;
    }
}
