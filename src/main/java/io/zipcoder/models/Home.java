package io.zipcoder.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Home {
    @Id
    @GeneratedValue
    Integer id;
    String address;
    String homeNumber;

    public Integer getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
    }
}
