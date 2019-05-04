package com.alpha.data.domain;


public class Address {
    private String street;
    private Integer number;

    public Address() {
    }

    public Address(String street, Integer number) {
        this.street = street;
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Address)) {
            return false;
        }
        Address address = (Address) obj;
        return number.equals(address.getNumber()) && street.equals(address.getStreet());
    }
}
