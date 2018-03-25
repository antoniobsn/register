package br.com.register.controller;

import br.com.register.controller.mapper.AddressMapper;
import br.com.register.controller.request.AddressRequest;
import br.com.register.model.Address;
import br.com.register.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;

/**
 * Created by asampaio on 25/03/18.
 */

@RestController
@RequestMapping("api/v1/adresses")
public class AddressController {


    private final AddressMapper mapper;
    private final AddressRepository repository;

    @Autowired
    public AddressController(AddressMapper mapper, AddressRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AddressRequest> create(@RequestBody AddressRequest request){

        Address address = mapper.convertAddressRequestToAddress(request);
        Address addressSaved = repository.saveAndFlush(address);
        AddressRequest addressRequest = mapper.convertAddressToAddressRequest(addressSaved);

        return new ResponseEntity<>(addressRequest, HttpStatus.CREATED);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<AddressRequest>> findAll(){
        return new ResponseEntity<>(mapper.convertAddressesToAddressesRequest(repository.findAll()), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<AddressRequest> findById(@PathVariable Long id) throws NoHandlerFoundException {

        Address address = repository.findOne(id);

        if(address == null){
            throw new NoHandlerFoundException(HttpStatus.NOT_FOUND.toString(), "api/v1/addresses/"+id, new HttpHeaders());
        }

        AddressRequest addressRequest = mapper.convertAddressToAddressRequest(address);

        return new ResponseEntity<>(addressRequest, HttpStatus.OK);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AddressRequest> update(@PathVariable Long id, @RequestBody AddressRequest request) {

        Address currentUser = mapper.convertAddressRequestToAddress(request);
        Address updatedUser = mapper.convertExistingAddressToCurrent(currentUser, id);

        AddressRequest addressResponse = mapper.convertAddressToAddressRequest(repository.saveAndFlush(updatedUser));

        return new ResponseEntity<>(addressResponse, HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<AddressRequest> delete(@PathVariable Long id){

        Address existingAddress = repository.findOne(id);
        repository.delete(existingAddress);
        AddressRequest deletedAddress = mapper.convertAddressToAddressRequest(existingAddress);

        return new ResponseEntity<>(deletedAddress, HttpStatus.OK);
    }
}
