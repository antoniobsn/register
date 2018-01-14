package br.com.register.controller;

import br.com.register.controller.mapper.CompanyMapper;
import br.com.register.controller.request.CompanyRequest;
import br.com.register.model.Company;
import br.com.register.repository.CompanyRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by asampaio on 14/01/18.
 */

@RestController
@RequestMapping("api/v1/companies")
public class CompanyController {

    private final CompanyRepository repository;
    private final CompanyMapper mapper;

    @Autowired
    public CompanyController(CompanyRepository repository, CompanyMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Company create(@RequestBody CompanyRequest request){
        return repository.saveAndFlush(mapper.convertCompanyRequestToCompany(request));
    }

    @GetMapping
    public List<Company> findAll(){
        return repository.findAll();
    }

    @GetMapping("{id}")
    public Company findById(@PathVariable Long id){
        return repository.findOne(id);
    }

    @PutMapping("{id}")
    public Company update(@PathVariable Long id, @RequestBody Company company){
        Company existingCompany = repository.findOne(id);
        BeanUtils.copyProperties(company, existingCompany);
        return repository.saveAndFlush(existingCompany);
    }

    @DeleteMapping("{id}")
    public Company delete(@PathVariable Long id){
        Company existingCompany = repository.findOne(id);
        repository.delete(existingCompany);
        return existingCompany;
    }


}
