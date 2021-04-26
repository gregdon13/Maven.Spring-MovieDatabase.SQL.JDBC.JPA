package io.zipcoder.controller;
import io.zipcoder.models.Person;
import io.zipcoder.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/people")
    public ResponseEntity<Person> createPerson(Person person) {
        return new ResponseEntity<>(personService.add(person), HttpStatus.CREATED);
    }

    @DeleteMapping("people/{id}")
    public ResponseEntity<Boolean> deletePerson(@PathVariable Integer id) {
        return new ResponseEntity<>(personService.remove(id), HttpStatus.OK);
    }

    @GetMapping("/people")
    public ResponseEntity<Iterable<Person>> getAllPersons() {
        return new ResponseEntity<>(personService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/people/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable Integer id) {
        return new ResponseEntity<>(personService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/people/reverselookup/{mobile}")
    public ResponseEntity<Person> getPersonByMobile(@PathVariable String mobile) {
        for (Person p : personService.getAll()) {
            if (p.getMobile().equals(mobile)) {
                Integer id = p.getId();
                return new ResponseEntity<>(personService.findById(id), HttpStatus.OK);
            }
        }
        return null;
    }

    @GetMapping("/people/surname")
    public ResponseEntity<Map<String, List<Person>>> getAllLastNames() {
        return new ResponseEntity<>(personService.lastNameMap(), HttpStatus.OK);
    }

    @GetMapping("/people/surname/{lastName}")
    public ResponseEntity<Iterable<Person>> findByLastName(@PathVariable String lastName) {
        return new ResponseEntity<>(personService.findAllWith(lastName), HttpStatus.OK);
    }

    @GetMapping("people/firstname/stats")
    public ResponseEntity<Map<String, Integer>> getFirstNameStats() {
        return new ResponseEntity<>(personService.firstNameCount(), HttpStatus.OK);
    }
}
