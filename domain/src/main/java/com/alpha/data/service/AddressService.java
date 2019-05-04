package com.alpha.data.service;

import com.alpha.data.domain.Address;
import com.alpha.data.repository.AddressRepository;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class AddressService {
    private static final Logger log = LogManager.getLogger(AddressService.class);
    @Autowired
    private AddressRepository addressRepository;

    public Integer statistic() {
        List<Address> allBacklogs = addressRepository.getAllAddresses();
        return allBacklogs == null ? 0 : allBacklogs.size();
    }

    public List<Address> checkStreet(String street) {
        return addressRepository.getAddressByStreet(street);
    }


    public void integrated() {
        List<Address> addresses = addressRepository.getAllAddresses();
        addresses.forEach(address -> log.info(address.getStreet() + ": " + address.getNumber()));

        Address address = new Address("xinjian", 12);
        addressRepository.addAddress(address);
        Long id = addressRepository.addAndReturnKey(address);
        Address xinjian = addressRepository.getAddress(id);
        log.info(xinjian.getStreet() + ": " + xinjian.getNumber());
        addressRepository.updateAddress(id, new Address("tiyu", 10));
        Address tiyu = addressRepository.getAddress(id);
        log.info(tiyu.getStreet() + ": " + tiyu.getNumber());
        addressRepository.deleteAddress(id);
        addressRepository.deleteAddressByStreet("xinjian");

        Integer count = addressRepository.count("xinjian");
        log.info("the count of record for street 'xinjian' is: " + count);

        List<Map<String, Object>> mapList = addressRepository.easyQuery();

        mapList.forEach(map -> log.info(map.toString()));
    }
}
