package ttl.larku.solutions.lab1;

import ttl.larku.dao.CustomerDAO;
import ttl.larku.dao.InMemoryCustomerDAO;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author whynot
 */
public class Lab1Solution {

    CustomerDAO customerDAO = new InMemoryCustomerDAO();

    public static void main(String[] args) {
        Lab1Solution l1s = new Lab1Solution();

        init(l1s.customerDAO);

        //l1s.sortComparable();
        l1s.sortComparator();
    }

    public void sortComparable() {
        List<Customer> customers = customerDAO.getAll();

//        Collections.sort(customers);

        for(Customer c: customers) {
            System.out.println(c);
        }
    }

    public void sortComparator() {
        List<Customer> customers = customerDAO.getAll();

//        int compare(T o1, T o2)

        Comparator<Customer> comp1 = (c1, c2) -> c1.getName().compareTo(c2.getName());

//        Collections.sort(customers, comp1);
        Collections.sort(customers, (c1, c2) -> c1.getName().compareTo(c2.getName()));

        for(Customer c: customers) {
            System.out.println(c);
        }
    }

    public static void init(CustomerDAO dao) {
       dao.insert(new Customer("Joe", LocalDate.of(2000, 10, 10)));
        dao.insert(new Customer("Sammy", LocalDate.of(1987, 8, 10), Customer.Status.NORMAL));
        dao.insert(new Customer("Darlene", LocalDate.of(1925, 8, 10), Customer.Status.PRIVILEGED, "383 29 929292"));
        dao.insert(new Customer("Rachna", LocalDate.of(2020, 10, 10), Customer.Status.RESTRICTED));
    }
}
