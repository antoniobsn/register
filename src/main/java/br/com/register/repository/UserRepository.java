package br.com.register.repository;

import br.com.register.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by asampaio on 07/01/18.
 */
public interface UserRepository extends JpaRepository<User, Long>{
}
