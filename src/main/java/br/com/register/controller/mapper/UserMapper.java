package br.com.register.controller.mapper;

import br.com.register.controller.request.UserRequest;
import br.com.register.model.Company;
import br.com.register.model.User;
import br.com.register.repository.CompanyRepository;
import br.com.register.repository.UserRepository;
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
    private final UserRepository userRepository;

    @Autowired
    public UserMapper(CompanyRepository companyRepository, UserRepository userRepository) {
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
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

    public User convertExistingToCurrent(User currentUser, Long id) {

        User existingUser = userRepository.findOne(id);

        existingUser.setCpf(currentUser.getCpf());
        existingUser.setName(currentUser.getName());
        existingUser.setEmail(currentUser.getEmail());
        existingUser.setOffice(currentUser.getOffice());
        existingUser.setCompany(companyRepository.findOne(currentUser.getCompany().getId()));

        return existingUser;
    }
}
