package io.zipcoder.service;

import io.zipcoder.models.Person;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PersonService {
    JdbcTemplate jdbcTemplate = new JdbcTemplate();

    public Iterable<Person> findAll() {
        String sql = "SELECT * FROM PERSON";

        return jdbcTemplate.query(sql,
                (rs, rowNum) ->
                new Person(
                        rs.getLong("id"),
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

    public int insertPerson(String firstName, String lastName, String mobile, Date birthday, Integer homeId) {
        String sql = "INSERT INTO PERSON (FIRST_NAME, LAST_NAME, MOBILE, BIRTHDAY, HOME_ID) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, firstName, lastName, mobile, birthday, homeId);
    }

    //NEEDS TO BE WRITTEN
    public void removeListOfPerson() {}

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
