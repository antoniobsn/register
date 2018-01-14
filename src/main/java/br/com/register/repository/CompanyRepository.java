package br.com.register.repository;

import br.com.register.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by asampaio on 14/01/18.
 */
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
