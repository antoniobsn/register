package br.com.register.model;

import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by asampaio on 14/01/18.
 */

@Entity
@Table(name = "COMPANIES")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @CNPJ
    private String CNPJ;

    private String fantasyName;

    private String reasonSocial;

    @Column(name = "created_at", columnDefinition = "DATE")
    private LocalDate createdAt;

    @Column(name = "updated_at", columnDefinition = "DATE")
    private LocalDate updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", nullable = true)
    private Address address;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "company")
    public Set<User> users = new HashSet<>();

    public Company() {}

    public Company(String CNPJ, String fantasyName, String reasonSocial) {
        this.CNPJ = CNPJ;
        this.fantasyName = fantasyName;
        this.reasonSocial = reasonSocial;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public String getFantasyName() {
        return fantasyName;
    }

    public String getReasonSocial() {
        return reasonSocial;
    }

    public Set<User> getUsers() {
        return users;
    }

    public Long getId() {
        return id;
    }
}
