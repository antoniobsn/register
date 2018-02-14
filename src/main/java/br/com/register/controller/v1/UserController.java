package br.com.register.controller.v1;

import br.com.register.controller.mapper.UserMapper;
import br.com.register.controller.request.UserRequest;
import br.com.register.model.User;
import br.com.register.repository.UserRepository;
import br.com.register.repository.UserRepositoryDefinition;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    public UserController(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserRequest> create(@RequestBody UserRequest request){

        User user = mapper.convertUserRequestToUser(request);
        User userSaved = repository.saveAndFlush(user);
        UserRequest userRequest = mapper.convertUserToUserRequest(userSaved);

        return new ResponseEntity<>(userRequest, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserRequest>> findAll(){
        return new ResponseEntity<List<UserRequest>>(
                mapper.convertUsersToUsersRequest(repository.findAll()), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserRequest> findById(@PathVariable Long id){

        UserRequest userRequest = mapper.convertUserToUserRequest(repository.findOne(id));

        return new ResponseEntity<>(userRequest, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserRequest> update(@PathVariable Long id, @RequestBody UserRequest request){

        User user = mapper.convertUserRequestToUser(request);
        User existingUser = repository.findOne(id);
        BeanUtils.copyProperties(user, existingUser);
        UserRequest userRequest = mapper.convertUserToUserRequest(repository.saveAndFlush(existingUser));

        return new ResponseEntity<>(userRequest, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<UserRequest> delete(@PathVariable Long id){

        User existingUser = repository.findOne(id);
        repository.delete(existingUser);
        UserRequest deletedUser = mapper.convertUserToUserRequest(existingUser);

        return new ResponseEntity<>(deletedUser, HttpStatus.OK);
    }

}
