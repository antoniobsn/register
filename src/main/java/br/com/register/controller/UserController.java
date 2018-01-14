package br.com.register.controller;

import br.com.register.controller.mapper.UserMapper;
import br.com.register.controller.request.UserRequest;
import br.com.register.model.User;
import br.com.register.repository.UserRepository;
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

    @Autowired
    public UserController(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
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
    public List<User> findAll(){
        return repository.findAll();
    }

    @GetMapping("{id}")
    public User findById(@PathVariable Long id){
        return repository.findOne(id);
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
