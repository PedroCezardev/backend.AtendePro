package com.backend.demo.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.backend.demo.model.User;

public interface UserRepository extends MongoRepository<User, String> {
    
    public User findByEmailAndPassword(String email, String password);

    public List<User> findByUsername(String username);

    public List<User> findByEmail(String email);

    public List<User> findByTelefone(String telefone);

}
