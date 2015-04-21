package de.zalando.social.shovel.web.Fake;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by soroosh on 4/21/15.
 */
public interface CustomerRepository extends MongoRepository<Customer, String> {

    public Customer findByFirstName(String firstName);
    public List<Customer> findByLastName(String lastName);

}