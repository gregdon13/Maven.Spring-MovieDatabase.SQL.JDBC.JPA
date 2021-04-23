package io.zipcoder.controller;

import io.zipcoder.models.Person;
import io.zipcoder.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/people")
    public ResponseEntity<Person> createPerson(Person newPerson) {
        return new ResponseEntity<>(personService.insertPerson(newPerson), HttpStatus.CREATED);
    }

    @PutMapping("/people/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Integer id, @RequestBody Person person, HttpServletResponse res) {
        Person out = personService.findPersonById(id);
        if (out == null) {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return new ResponseEntity<>(personService.updatePerson(person), HttpStatus.OK);
    }

    @GetMapping("/people/{id}")
    public ResponseEntity<Person> findPerson(@PathVariable Integer id) {
        return new ResponseEntity<>(personService.findPersonById(id), HttpStatus.OK);
    }

    @GetMapping("/people")
    public ResponseEntity<Iterable<Person>> findAllPerson() {
        return new ResponseEntity<>(personService.findAll(), HttpStatus.OK);
    }
}
