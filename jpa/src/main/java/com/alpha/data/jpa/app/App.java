package com.alpha.data.jpa.app;

import com.alpha.data.domain.AddressEntity;
import com.alpha.data.domain.Cooperator;

import javax.persistence.*;
import java.util.List;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("test-jpa");
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        AddressEntity address = manager.find(AddressEntity.class, 1);
        System.out.println(address.getStreet());
        address.setStreet("gaoxin");

        AddressEntity newAddress = new AddressEntity();
        newAddress.setStreet("xinjian");
        newAddress.setNumber(1);
        manager.persist(newAddress);

        Query query = manager.createQuery("select address from AddressEntity address where address.street like ?1");
        query.setParameter(1, "gaoxin");
        List<AddressEntity> list = query.getResultList();
        list.forEach(e -> System.out.println(e.getNumber()));

        Cooperator cooperator = new Cooperator("alpha", "alpha@qq.com");
        manager.persist(cooperator);


        transaction.commit();
        manager.close();

    }
}
