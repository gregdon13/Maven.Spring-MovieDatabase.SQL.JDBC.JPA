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
}
