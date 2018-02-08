package br.com.register.repository;

import br.com.register.model.User;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

/**
 * Created by asampaio on 18/01/18.
 */

@RepositoryDefinition(domainClass = User.class, idClass = Long.class)
public interface UserRepositoryDefinition {

    User save(User user);

    List<User> save(List<Long> ids);

    User findById(Long id);

    User findOne(Long id);

    List<User> findAll();

    Iterable<User> findAll(Iterable ids);

}
