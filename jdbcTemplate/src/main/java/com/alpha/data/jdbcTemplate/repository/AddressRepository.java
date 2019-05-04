package com.alpha.data.jdbcTemplate.repository;

import com.alpha.data.domain.Address;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AddressRepository {
    List<Address> getAllAddresses();

    Address getAddress(Long id);

    List<Address> getAddressByStreet(String street);

    void addAddress(Address address);

    void deleteAddress(Long id);


    void deleteAddressByStreet(String street);

    void updateAddress(Long id, Address newAddress);

    void updateNumber(Long id, Integer newNumber);

    Integer count(String street);

    List<Map<String, Object>> easyQuery();

    Long addAndReturnKey(Address address);
}
