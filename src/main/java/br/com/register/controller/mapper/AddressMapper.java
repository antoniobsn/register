package br.com.register.controller.mapper;

import br.com.register.controller.request.AddressRequest;
import br.com.register.model.Address;
import org.springframework.util.MultiValueMap;

import java.util.List;

/**
 * Created by asampaio on 25/03/18.
 */
public class AddressMapper {
    public Address convertAddressRequestToAddress(AddressRequest request) {
        return new Address(request.getCEP(), request.getCountry(), request.getState(), request.getCity(),
                request.getNeighborhood(), request.getStreet(), request.getNumber(), request.getComplement());
    }

    public AddressRequest convertAddressToAddressRequest(Address addressSaved) {
        return null;
    }

    public MultiValueMap<String, String> convertAddressesToAddressesRequest(List<Address> all) {
        return null;
    }

    public Address convertExistingAddressToCurrent(Address currentAddress, Long id) {
        return null;
    }
}
