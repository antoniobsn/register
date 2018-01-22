package br.com.register.controller.v1;

import br.com.register.controller.mapper.UserMapper;
import br.com.register.controller.request.UserRequest;
import br.com.register.model.User;
import br.com.register.repository.UserRepository;
import br.com.register.repository.UserRepositoryDefinition;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by asampaio on 30/12/17.
 */

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final UserRepositoryDefinition repositoryDefinition;

    @Autowired
    public UserController(UserRepository repository, UserMapper mapper, UserRepositoryDefinition repositoryDefinition) {
        this.repository = repository;
        this.mapper = mapper;
        this.repositoryDefinition = repositoryDefinition;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserRequest create(@RequestBody UserRequest request){
        User user = mapper.convertUserRequestToUserFull(request);
        User userSaved = repository.saveAndFlush(user);
        UserRequest userRequest = mapper.convertUserToUserRequest(userSaved);
        return userRequest;
    }

    @GetMapping
    public List<UserRequest> findAll(){
        return mapper.convertUsersToUsersRequest(repository.findAll());
    }

    @GetMapping("{id}")
    public UserRequest findById(@PathVariable Long id){
        return mapper.convertUserToUserRequest(repository.findOne(id));
    }

    @PutMapping("{id}")
    public User update(@PathVariable Long id, @RequestBody UserRequest request){
        User user = mapper.convertUserRequestToUserFull(request);
        User existingUser = repository.findOne(id);
        BeanUtils.copyProperties(user, existingUser);
        return repository.saveAndFlush(existingUser);
    }

    @DeleteMapping("{id}")
    public User delete(@PathVariable Long id){
        User existingUser = repository.findOne(id);
        repository.delete(existingUser);
        return existingUser;
    }

}
