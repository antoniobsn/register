package br.com.register.model;

import br.com.register.enums.Office;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by asampaio on 30/12/17.
 */

@Entity
@Table(name = "USERS")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @CPF
    private String cpf;

    private String name;

    private String email;

    @Enumerated(EnumType.STRING)
    private Office office;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = true)
    private Company company;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", columnDefinition = "DATE")
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", columnDefinition = "DATE")
    private Date updatedAt;

    public User() {
    }

    public User(String cpf, String name, String email, Office office, Company company) {
        this.cpf = cpf;
        this.name = name;
        this.email = email;
        this.office = office;
        this.company = company;
    }

    public User convertExistingToCurrent(User existingUser, User currentUser){

        existingUser.cpf = currentUser.getCpf();
        existingUser.name = currentUser.getName();
        existingUser.email = currentUser.getEmail();
        existingUser.office = currentUser.getOffice();
        existingUser.company = currentUser.getCompany();

        return  existingUser;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
