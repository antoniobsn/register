package br.com.register.controller.mapper;

import br.com.register.controller.request.UserRequest;
import br.com.register.model.Company;
import br.com.register.model.User;
import br.com.register.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asampaio on 07/01/18.
 */

@Component
public class UserMapper {

    private final CompanyRepository companyRepository;

    @Autowired
    public UserMapper(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public User convertUserRequestToUser(UserRequest request){
        Company company = companyRepository.findOne(request.getCompanyId());
        return new User(request.getCpf(), request.getName(), request.getEmail(), request.getOffice(), company);
    }

    public UserRequest convertUserToUserRequest(User user){
        return new UserRequest(user.getCpf(), user.getName(), user.getEmail(), user.getOffice(), user.getCompany().getId());
    }

    public List<UserRequest> convertUsersToUsersRequest(Iterable<User> users){
        List<UserRequest> usersRequest = new ArrayList<>();

        for (User user : users){
            usersRequest.add(convertUserToUserRequest(user));
        }
        return usersRequest;
    }
}
