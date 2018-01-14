package br.com.register.controller.mapper;

import br.com.register.controller.request.CompanyRequest;
import br.com.register.model.Company;
import org.springframework.stereotype.Component;

/**
 * Created by asampaio on 14/01/18.
 */

@Component
public class CompanyMapper {

    public Company convertCompanyRequestToCompany(CompanyRequest request) {
        return new Company(request.getCNPJ(), request.getFantasyName(), request.getReasonSocial());
    }
}
