package br.com.register.controller.request;

/**
 * Created by asampaio on 25/03/18.
 */
public class AddressRequest {

    private Long CEP;
    private String country;
    private String state;
    private String city;
    private String neighborhood;
    private String street;
    private int number;
    private String complement;

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
}