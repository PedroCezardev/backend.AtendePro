package com.backend.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.demo.exception.InvalidUserException;
import com.backend.demo.model.User;
import com.backend.demo.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public User insertUser(User user) {
        validateUser(user);
        return userRepository.save(user);
    }

    public User loginByUser(String email, String password) throws InvalidUserException {
        User userSearch = userRepository.findByEmailAndPassword(email, password);
        if (userSearch != null) {
            return userSearch;
        }
        throw new InvalidUserException("Usuario ou senha invalido");
    }

    public List<User> getAllUser() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new InvalidUserException("Nenhum usuario encontrado.");
        }
        return users;
    }

    public List<User> getByUsername(String username) {
        List<User> users = userRepository.findByUsername(username);
        if (users.isEmpty()) {
            throw new InvalidUserException("Nenhum nome de usuario encontrado");
        }
        return users;
    }

    public List<User> getByEmail(String email) {
        List<User> users = userRepository.findByEmail(email);
        if (users.isEmpty()) {
            throw new InvalidUserException("Nenhum email de usuario encontrado.");
        }
        return users;
    }

    public List<User> getByTelefone(String telefone) {
        List<User> users = userRepository.findByTelefone(telefone);
        if (users.isEmpty()) {
            throw new InvalidUserException("Nenhum telefone de usuario encontrado.");
        }
        return users;
    }

    public void deleteById(String id) {
        if (!userRepository.existsById(id)) {
            throw new InvalidUserException("O usuario nao foi encontrado no sistema.");
        }
        userRepository.deleteById(id);
        System.out.println("O usuario foi deletado com sucesso!");
    }

    public User upatedUserById(String id, User userDetails) {

        if (userDetails == null) {
            throw new InvalidUserException("Os detalhes do usuario nao podem ser nulos.");
        }

        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new InvalidUserException("User nao encontrado com id: " + id);
        }

        validateUser(userDetails);
        User user = optionalUser.get();

        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());
        user.setTelefone(userDetails.getTelefone());

        User updatedUser = userRepository.save(user);
        return updatedUser;
    }

    private void validateUser(User user) {
        if (user.getUsername() == null || user.getUsername().isEmpty() || user.getUsername().length() > 80) {
            throw new InvalidUserException("O nome do usuario nao pode ser nulo, vazio ou maior que 80 caracteres.");
        }
        if (user.getEmail() == null || user.getEmail().isEmpty() || user.getEmail().length() > 80) {
            throw new InvalidUserException("O Email do usuario nao pode ser nulo, vazio ou maior que 80 caracteres.");
        }
        if (user.getTelefone() == null || user.getTelefone().isEmpty() || user.getTelefone().length() > 80) {
            throw new InvalidUserException("O nome do usuario nao pode ser nulo, vazio ou maior que 80 caracteres.");
        }
    }

}
