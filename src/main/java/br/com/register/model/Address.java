package br.com.register.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by asampaio on 25/03/18.
 */

@Entity
@Table(name = "ADRESSES")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long CEP;
    private String country;
    private String state;
    private String city;
    private String neighborhood;
    private String street;
    private int number;
    private String complement;

    @Column(name = "created_at", columnDefinition = "DATE")
    private LocalDate createdAt;

    @Column(name = "updated_at", columnDefinition = "DATE")
    private LocalDate updatedAt;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "address")
    public Set<Company> companies = new HashSet<>();

    public Address() {
    }

    public Address(Long CEP, String country, String state, String city, String neighborhood, String street, int number, String complement) {
        this.CEP = CEP;
        this.country = country;
        this.state = state;
        this.city = city;
        this.neighborhood = neighborhood;
        this.street = street;
        this.number = number;
        this.complement = complement;
    }

    public Long getCEP() {
        return CEP;
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getStreet() {
        return street;
    }

    public int getNumber() {
        return number;
    }

    public String getComplement() {
        return complement;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public Set<Company> getCompanies() {
        return companies;
    }
}
