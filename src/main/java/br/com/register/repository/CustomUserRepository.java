package br.com.register.repository;

import br.com.register.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by asampaio on 11/03/18.
 */

@Service
public class CustomUserRepository {

    private final UserRepository repository;

    @Autowired
    public CustomUserRepository(UserRepository repository) {
        this.repository = repository;
    }

    public Optional<User> findOne(Long id) {
        return Optional.of(repository.findOne(id));
    }
}
