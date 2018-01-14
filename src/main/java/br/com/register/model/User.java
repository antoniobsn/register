package br.com.register.model;

import br.com.register.enums.Office;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by asampaio on 30/12/17.
 */

@Entity
@Table(name = "USERS")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String cpf;
    private String name;
    private String email;

    @Enumerated(EnumType.STRING)
    private Office office;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = true)
    private Company company;

    @Column(name = "created_at", columnDefinition = "DATE")
    private LocalDate createdAt;

    @Column(name = "updated_at", columnDefinition = "DATE")
    private LocalDate updatedAt;

    public User() {
    }

    public User(String cpf, String name, String email, Office office) {
        this.cpf = cpf;
        this.name = name;
        this.email = email;
        this.office = office;
    }

    public User(String cpf, String name, String email, Office office, Company company) {
        this.cpf = cpf;
        this.name = name;
        this.email = email;
        this.office = office;
        this.company = company;
    }

    @PrePersist
    public void onPrePersist() {
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    @PreUpdate
    public void onPreUpdate() {
        this.updatedAt = LocalDate.now();
    }

    public String getCpf() {
        return cpf;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Office getOffice() {
        return office;
    }

    public Company getCompany() {
        return company;
    }
}
