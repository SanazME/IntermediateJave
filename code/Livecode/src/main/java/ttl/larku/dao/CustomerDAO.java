package ttl.larku.dao;

import ttl.larku.domain.Student;
import ttl.larku.solutions.lab1.Customer;

import java.util.List;

/**
 * @author whynot
 */
public interface CustomerDAO {
    Customer insert(Customer s);

    Customer get(int id);

    List<Customer> getAll();
}
