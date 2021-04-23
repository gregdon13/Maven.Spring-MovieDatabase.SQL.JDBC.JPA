package io.zipcoder.service;

import io.zipcoder.models.Person;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
