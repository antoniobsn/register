package br.com.register.controller.v2;

import br.com.register.controller.mapper.UserMapper;
import br.com.register.controller.request.UserRequest;
import br.com.register.model.User;
import br.com.register.repository.UserRepositoryDefinition;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by asampaio on 21/01/18.
 */

@RestController
@RequestMapping("api/v2/users")
public class UserControllerV2 {

    private final UserRepositoryDefinition repository;
    private final UserMapper mapper;

    public UserControllerV2(UserRepositoryDefinition repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @GetMapping
    public List<UserRequest> findByList(@RequestParam List<Long> ids){
        Iterable<User> all = repository.findAll(ids);
        return mapper.convertUsersToUsersRequest(repository.findAll(ids));
    }

    @GetMapping("{id}/show")
    public UserRequest showFindById(@PathVariable Long id){
        return mapper.convertUserToUserRequest(repository.findById(id));
    }

    @GetMapping(params = "id")
    public UserRequest findById(@RequestParam Long id){

        User user = repository.findOne(id);
        UserRequest userRequest = mapper.convertUserToUserRequest(user);

        return userRequest;
    }
}
