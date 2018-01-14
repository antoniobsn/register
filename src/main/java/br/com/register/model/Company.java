package br.com.register.model;

import javax.persistence.*;
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

    private String CNPJ;
    private String fantasyName;
    private String reasonSocial;

    @Column(name = "created_at", columnDefinition = "DATE")
    private LocalDate createdAt;

    @Column(name = "updated_at", columnDefinition = "DATE")
    private LocalDate updatedAt;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "company")
    public Set<User> users = new HashSet<>();

    public Company() {}

    public Company(String CNPJ, String fantasyName, String reasonSocial) {
        this.CNPJ = CNPJ;
        this.fantasyName = fantasyName;
        this.reasonSocial = reasonSocial;
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
