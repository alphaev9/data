package com.alpha.data.jdbcTemplate.service;

import com.alpha.data.jdbcTemplate.repository.AddressRepository;
import com.alpha.springmvc.domain.Address;
import com.alpha.springmvc.domain.Backlog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class AddressServiceTest {
    @Mock
    private AddressRepository mockAddressRepository;
    @InjectMocks
    private AddressService mockAddressService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void statistic() {
        List<Address> addresses = new ArrayList<>();
        addresses.add(new Address());
        addresses.add(new Address());
        addresses.add(new Address());
        when(mockAddressRepository.getAllAddresses()).thenReturn(addresses);
        Integer count = mockAddressService.statistic();
        assertEquals(3, count);

        when(mockAddressRepository.getAllAddresses()).thenReturn(null);
        Integer count1 = mockAddressService.statistic();
        assertEquals(0, count1);
    }
}