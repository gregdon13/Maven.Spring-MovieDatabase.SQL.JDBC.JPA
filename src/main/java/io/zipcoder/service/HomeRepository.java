package io.zipcoder.service;

import io.zipcoder.models.Home;
import org.springframework.data.repository.CrudRepository;

public interface HomeRepository extends CrudRepository<Home, Integer> {
}
