package io.zipcoder.service;

import io.zipcoder.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.*;


@RestController
public class PersonService {
    private final PersonRepository personRepo;

    @Autowired
    public PersonService(PersonRepository personRepo) {
        this.personRepo = personRepo;
    }

    public Iterable<Person> getAll() {
        return personRepo.findAll();
    }

    public Person add(Person person) {
        return personRepo.save(person);
    }

    public Boolean remove(Integer id) {
        personRepo.delete(id);
        return true;
    }

    public Boolean removeList(List<Person> personList) {
        personRepo.delete(personList);
        return true;
    }

    public Person update(@PathVariable Integer id, Person person) {
        Person holder = personRepo.findOne(id);
        holder.setFirstName(person.getFirstName());
        holder.setLastName(person.getLastName());
        holder.setMobile(person.getMobile());
        holder.setBirthday(person.getBirthday());
        holder.setHomeId(person.getHomeId());
        return personRepo.save(holder);
    }

    public Person addToHouse(@PathVariable Integer personId, @PathVariable Integer homeId) {
        Person holder = personRepo.findOne(personId);
        holder.setHomeId(homeId);
        return personRepo.save(holder);
    }

    public Iterable<Person> findAllWith(String firstName) {
        ArrayList<Person> personArrayList = new ArrayList<>();
        for (Person p : personRepo.findAll()) {
            if (p.getFirstName().equals(firstName)) {
                personArrayList.add(p);
            }
        }
        return personArrayList;
    }

    public Person findById(Integer id) {
        return personRepo.findOne(id);
    }

    public Map<String, List<Person>> lastNameMap() {
        HashMap<String, List<Person>> nameMap = new HashMap<>();
        for (Person p : personRepo.findAll()) {
            if (!nameMap.containsKey(p.getLastName())) {
                nameMap.put(p.getLastName(), new ArrayList<Person>());
            }
            nameMap.get(p.getLastName()).add(p);
        }
        return nameMap;
    }

    public Map<String, Integer> firstNameCount() {
        HashMap<String, Integer> countMap = new HashMap<>();
        for (Person p : personRepo.findAll()) {
            if (!countMap.containsKey(p.getFirstName())) {
                countMap.put(p.getFirstName(), 1);
            }
            countMap.put(p.getFirstName(), countMap.get(p.getFirstName() + 1));
        }
        return countMap;
    }


}
