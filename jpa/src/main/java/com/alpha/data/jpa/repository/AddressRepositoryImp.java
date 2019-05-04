package com.alpha.data.jpa.repository;

import com.alpha.data.domain.Address;
import com.alpha.data.repository.AddressRepository;

import java.util.List;
import java.util.Map;

public class AddressRepositoryImp implements AddressRepository {

    @Override
    public List<Address> getAllAddresses() {
        return null;
    }

    @Override
    public Address getAddress(Long id) {
        return null;
    }

    @Override
    public List<Address> getAddressByStreet(String street) {
        return null;
    }

    @Override
    public void addAddress(Address address) {

    }

    @Override
    public void deleteAddress(Long id) {

    }

    @Override
    public void deleteAddressByStreet(String street) {

    }

    @Override
    public void updateAddress(Long id, Address newAddress) {

    }

    @Override
    public void updateNumber(Long id, Integer newNumber) {

    }

    @Override
    public Integer count(String street) {
        return null;
    }

    @Override
    public List<Map<String, Object>> easyQuery() {
        return null;
    }

    @Override
    public Long addAndReturnKey(Address address) {
        return null;
    }
}
