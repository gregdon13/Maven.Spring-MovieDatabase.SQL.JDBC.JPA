package io.zipcoder.service;

import io.zipcoder.models.Person;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.*;


//Why the hell are you not using JPA crud repository????
@Service
public class PersonService {
    JdbcTemplate jdbcTemplate = new JdbcTemplate();

    public Iterable<Person> findAll() {
        String sql = "SELECT * FROM PERSON";

        return jdbcTemplate.query(sql,
                (rs, rowNum) ->
                new Person(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("mobile"),
                        rs.getDate("birthday"),
                        rs.getInt("home_id")
                ));
    }

    public Person findPersonById (Integer id) {
        String sql = "Select * from person where id = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{id}, Person.class);
    }

    //SKETCHY...
    public Person insertPerson(@RequestBody Person person) {
        String sql = "INSERT INTO PERSON (FIRST_NAME, LAST_NAME, MOBILE, BIRTHDAY, HOME_ID) VALUES (?, ?, ?, ?, ?)";
        String returnSql = "SELECT * FROM PERSON WHERE ID = ?";
        int newId = jdbcTemplate.update(sql, person.getFirstName(), person.getLastName(), person.getMobile(), person.getBirthday(), person.getHomeId());
        return jdbcTemplate.queryForObject(returnSql, new Object[]{newId}, Person.class);
    }

    public Person updatePerson(@RequestBody Person person) {
        String sql = "UPDATE PERSON SET FIRST_NAME = ?, LAST_NAME = ?, " +
                "MOBILE = ?, BIRTHDAY = ?, HOME_ID = ? WHERE ID = ?";
        jdbcTemplate.update(sql, person.getFirstName(), person.getLastName(),
                person.getMobile(), person.getBirthday(), person.getHomeId(), person.getId());
        String returnSql = "SELECT * FROM PERSON WHERE ID = ?";
        return jdbcTemplate.queryForObject(returnSql, new Object[]{person.getId()}, Person.class);
    }

    public void removePerson(Integer id) {
        String sql = "delete from person where id = ?";
        jdbcTemplate.update(sql, id);
    }

    public void removeListOfPerson(List<Person> personList) {
        String sql = "delete from person where id = ?";
        for (Person p : personList) {
            jdbcTemplate.update(sql, p.getId());
        }
    }

    public Person findByFirstName(String firstName) {
        String sql = "Select * from person where firstName = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{firstName}, Person.class);
    }

    public Person findByLastName(String lastName) {
        String sql = "Select * from person where lastName = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{lastName}, Person.class);
    }

    public Person findByBirthday(Date birthday) {
        String sql = "Select * from person where birthday = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{birthday}, Person.class);
    }

    public Map<String, List<Person>> findPeopleWithLastName() {
        Map<String, List<Person>> out = new HashMap<>();
        String sql = "select distinct last_name from person";
        List<String> lastNameList = jdbcTemplate.query(sql, (rs, rowNum) ->
                        rs.getString("last_name"));
        for (String last : lastNameList) {
            out.put(last, new ArrayList<>());
        }
        Iterable<Person> allPersons = findAll();
        for (Person p : allPersons) {
            for (String s : out.keySet()) {
                if (p.getLastName().equals(s)) {
                    out.get(s).add(p);
                }
            }
        }
        return out;
    }

    public Map<String, Integer> countNumberOfFirstName() {
        Map<String, Integer> out = new HashMap<>();
        String sql = "select distinct first_name from person";
        List<String> firstNameList = jdbcTemplate.query(sql, (rs, rowNum) ->
                rs.getString("first_name"));
        for (String first : firstNameList) {
            out.put(first, 0);
        }
        Iterable<Person> allPersons = findAll();
        for (Person p : allPersons) {
            for (String s : out.keySet()) {
                if (p.getFirstName().equals(s)) {
                    out.put(s, out.get(s) + 1);
                }
            }
        }
        return out;
    }
}
