package br.com.register.controller.request;

import br.com.register.enums.Office;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by asampaio on 07/01/18.
 */
public class UserRequest {

    @JsonProperty("cpf")
    private final String cpf;

    @JsonProperty("name")
    private final String name;

    @JsonProperty("email")
    private final String email;

    @JsonProperty("office")
    private final Office office;

    @JsonProperty("company_id")
    private Long companyId;

    @JsonCreator
    public UserRequest(
            @JsonProperty("cpf") String cpf,
            @JsonProperty("name") String name,
            @JsonProperty("email") String email,
            @JsonProperty("office") Office office,
            @JsonProperty("company_id") Long companyId) {
        this.cpf = cpf;
        this.name = name;
        this.email = email;
        this.office = office;
        this.companyId = companyId;
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

    public Long getCompanyId() {
        return companyId;
    }
}
