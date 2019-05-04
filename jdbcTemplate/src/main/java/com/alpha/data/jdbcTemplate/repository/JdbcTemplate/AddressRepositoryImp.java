package com.alpha.data.jdbcTemplate.repository.JdbcTemplate;

import com.alpha.data.domain.Address;
import com.alpha.data.jdbcTemplate.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class AddressRepositoryImp implements AddressRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Address> getAllAddresses() {
        List<Address> addresses = jdbcTemplate.query("select * from address", (resultSet, i) -> {
            Address address = mapRow(resultSet, 0);
            return address;
        });
        return addresses;
    }

    @Override
    public Address getAddress(Long id) {
        return jdbcTemplate.queryForObject("select * from address where id=?", new Object[]{id}, (resultSet, i) -> {
            Address address = mapRow(resultSet, 0);
            return address;
        });
    }

    @Override
    public List<Address> getAddressByStreet(String street) {
        List<Address> addresses = jdbcTemplate.query("select * from address where street=?",
                preparedStatement -> preparedStatement.setString(1, street),
                AddressRepositoryImp::mapRow);
        return addresses;
    }

    @Override
    public void addAddress(Address address) {
        jdbcTemplate.update("insert into address(street, number) value (?,?)", address.getStreet(), address.getNumber());
        jdbcTemplate.update("insert into address(street, number) value (?,?)", preparedStatement -> {
            preparedStatement.setString(1, address.getStreet());
            preparedStatement.setInt(2, address.getNumber() + 10);
        });
    }

    @Override
    public void deleteAddress(Long id) {
        jdbcTemplate.update("delete from address where id=?", id);
    }

    @Override
    public void deleteAddressByStreet(String street) {
        jdbcTemplate.update("delete from address where street=?", preparedStatement -> preparedStatement.setString(1, street));
    }

    @Override
    public void updateAddress(Long id, Address newAddress) {
        jdbcTemplate.update("update address set street=?,number=? where id=?", newAddress.getStreet(), newAddress.getNumber(), id);
    }

    @Override
    public void updateNumber(Long id, Integer newNumber) {
        jdbcTemplate.update("update address set number=? where id=?", preparedStatement -> {
            preparedStatement.setInt(1, newNumber);
            preparedStatement.setLong(2, id);
        });
    }

    @Override
    public Integer count(String street) {
        String sql = "select count(*) from address where street=:street";
        Map<String, String> params = Collections.singletonMap("street", street);
        return namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
    }

    @Override
    public List<Map<String, Object>> easyQuery() {
        List<Map<String, Object>> addresses = jdbcTemplate.queryForList("select * from address");
        return addresses;
    }

    @Override
    public Long addAndReturnKey(Address address) {
        String sql = "insert into address(street, number) value (?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, address.getStreet());
            ps.setInt(2, address.getNumber());
            return ps;
        }, keyHolder);
        return (Long) keyHolder.getKey();
    }

    private static Address mapRow(ResultSet resultSet, int i) throws SQLException {
        Address address = new Address();
        address.setStreet(resultSet.getString(2));
        address.setNumber(resultSet.getInt(3));
        return address;
    }
}
