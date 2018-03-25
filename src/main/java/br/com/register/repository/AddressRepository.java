package br.com.register.repository;

import br.com.register.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by asampaio on 25/03/18.
 */
public interface AddressRepository extends JpaRepository<Address, Long>{
}
