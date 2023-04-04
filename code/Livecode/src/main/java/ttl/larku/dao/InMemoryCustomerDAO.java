package ttl.larku.dao;

import ttl.larku.domain.Student;
import ttl.larku.solutions.lab1.Customer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author whynot
 */
public class InMemoryCustomerDAO implements CustomerDAO {

    //    private List<Student> students = new ArrayList<>();
    private Map<Integer, Customer> students = new HashMap<>();

    private int nextId = 1;

    private List<Customer> stuff = new ArrayList<>();

    public InMemoryCustomerDAO() {
        stuff.add(new Customer("one", LocalDate.now()));
    }

    @Override
    public Customer insert(Customer s) {
        int id = nextId++;
        //TODO - generate ID
        //s.setName("InMem:" + s.getName());
        s.setId(id);
        students.put(s.getId(), s);

        return s;
    }

    @Override
    public Customer get(int id) {
        return students.get(id);
    }

    @Override
    public List<Customer> getAll() {
        //return List.copyOf(students.values());
        return new ArrayList<>(students.values());
    }

    //    public Map<Integer, Student> getAll() {
    public Map<Integer, Customer> oldGetAll() {
        Map<Integer, Customer> copy = new HashMap<>(students);

        return copy;
    }

    public List<Customer> getStuff() {
        //return new ArrayList<>(stuff);
        return Collections.unmodifiableList(stuff);
    }
}
