package com.alpha.data.jpa;


import com.alpha.data.domain.AddressEntity;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.*;
import java.util.List;

public class JpaTest {
    private EntityManager entityManager;
    private EntityTransaction transaction;
    private static final Logger log = LogManager.getLogger(JpaTest.class);

    @BeforeEach
    void setUp() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("test-jpa");
        entityManager = factory.createEntityManager();
        transaction = entityManager.getTransaction();
        transaction.begin();
    }


    @Test
    void findById() {
        AddressEntity address = entityManager.find(AddressEntity.class, 2);
        log.info(address.getStreet());
    }

    @Test
    void insert() {
        AddressEntity address = new AddressEntity();
        address.setStreet("insert");
        address.setNumber(1);
        entityManager.persist(address);
    }

    @Test
    void update() {
        AddressEntity address = entityManager.find(AddressEntity.class, 1);
        address.setStreet("update");
    }

    @Test
    void delete() {
        AddressEntity address = entityManager.find(AddressEntity.class, 1);
        entityManager.remove(address);
    }

    @Test
    void jpql() {
        Query query = entityManager.createQuery("select address from AddressEntity address");
        List<AddressEntity> addressEntities = query.getResultList();
        addressEntities.forEach(addressEntity -> log.info(addressEntity.getStreet() + ": " + addressEntity.getNumber()));

        List<AddressEntity> gaoxin = entityManager.createQuery("select address from AddressEntity address where address.street like ?1")
                .setParameter(1, "gaoxin")
                .getResultList();
        gaoxin.forEach(addressEntity -> log.info(addressEntity.getStreet() + ": " + addressEntity.getNumber()));

    }

    @Test
    void name() {

    }

    @AfterEach
    void tearDown() {
        transaction.commit();
        entityManager.close();
    }
}
