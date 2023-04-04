package ttl.larku;

import org.junit.jupiter.api.Test;
import ttl.larku.app.RegistrationApp;
import ttl.larku.domain.Student;
import ttl.larku.service.StudentService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author whynot
 */
public class LambdaLabSolutions {

    /*
    4.Write a method to return the names of customers who have a status of Privileged. Use
Streams.
     */

    @Test
    public void lab4() {
        StudentService ss = new StudentService();
        RegistrationApp.init(ss);

        List<Student> students = ss.getAllStudents();

        List<String> names = students.stream()
                .filter(s -> s.getStatus() == Student.Status.FULL_TIME)
                .map(s -> s.getName())
                .collect(Collectors.toList());
    }

    /*
    5.Write a method to return a list of the ages of all Customers who have a status of
Normal.
     */
    @Test
    public void lab5() {
        StudentService ss = new StudentService();
        RegistrationApp.init(ss);

        List<Student> students = ss.getAllStudents();

        List<Long> ages = students.stream()
                .filter(s -> s.getStatus() == Student.Status.FULL_TIME)
                .map(s -> s.getDob().until(LocalDate.now(), ChronoUnit.YEARS))
                .collect(Collectors.toList());
    }

    /*
    6.Write a method to return the number of customers who are 20 years or older. To
calculate number of years from a LocalDate use:
myDate.until(LocalDate.now(), ChronoUnit.YEARS)
     */

    @Test
    public void lab6() {
        StudentService ss = new StudentService();
        RegistrationApp.init(ss);

        List<Student> students = ss.getAllStudents();

        long count20OrGreater = students.stream()
                .filter(s -> s.getDob().until(LocalDate.now(), ChronoUnit.YEARS) >= 20)
                .count();
    }
}
