package br.com.register.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by asampaio on 14/01/18.
 */
public class CompanyRequest {

    @JsonProperty("cnpj")
    private final String CNPJ;

    @JsonProperty("fantasy_name")
    private final String fantasyName;

    @JsonProperty("reason_social")
    private final String reasonSocial;

    public CompanyRequest(
            @JsonProperty("cnpj")String CNPJ,
            @JsonProperty("fantasy_name") String fantasyName,
            @JsonProperty("reason_social") String reasonSocial) {
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
}
