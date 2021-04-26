package io.zipcoder.service;

import io.zipcoder.models.Home;
import io.zipcoder.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
public class HomeService {
    private final HomeRepository homeRepository;
    private final PersonRepository personRepository;

    @Autowired
    public HomeService(HomeRepository homeRepository, PersonRepository personRepository) {
        this.personRepository = personRepository;
        this.homeRepository = homeRepository;
    }

    public Home addHome(Home home) {
        return homeRepository.save(home);
    }

    public Boolean removeHome(Home home) {
        homeRepository.delete(home);
        return true;
    }

    public Boolean removeManyHomes(List<Home> homeList) {
        homeRepository.delete(homeList);
        return true;
    }

    public Home updateHome(@PathVariable Integer id, Home home) {
        Home temp = homeRepository.findOne(id);
        temp.setAddress(home.getAddress());
        temp.setHomeNumber(home.getHomeNumber());
        return homeRepository.save(temp);
    }

    public Home findById(@PathVariable Integer id) {
        return homeRepository.findOne(id);
    }

    public Home findByNumber(String homeNumber) {
        for (Home home : homeRepository.findAll()) {
            if (home.getHomeNumber().equals(homeNumber)) {
                return homeRepository.findOne(home.getId());
            }
        }
        return null;
    }

    public Home findByAddress(String address) {
        for (Home home : homeRepository.findAll()) {
            if (home.getAddress().equals(address)) {
                return homeRepository.findOne(home.getId());
            }
        }
        return null;
    }

    public Home findByPerson(Integer id) {
        Person temp = personRepository.findOne(id);
        Integer houseId = temp.getHomeId();
        return homeRepository.findOne(houseId);
    }

    public List<Person> findHouseMembers(Home home) {
        Integer houseId = home.getId();
        List<Person> houseMembers = new ArrayList<>();
        for (Person p : personRepository.findAll()) {
            if (p.getHomeId().equals(houseId)) {
                houseMembers.add(p);
            }
        }
        return houseMembers;
    }
}
