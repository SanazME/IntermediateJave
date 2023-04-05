package ttl.larku;

import org.junit.jupiter.api.Test;
import ttl.larku.app.RegistrationApp;
import ttl.larku.domain.Student;
import ttl.larku.service.StudentService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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

    /*
    7.Write a method to calculate the average age of Customers who have the status
Restricted.
     */
    @Test
    public void lab7() {
        StudentService ss = new StudentService();
        RegistrationApp.init(ss);

        List<Student> students = ss.getAllStudents();

        students.stream()
                .filter(s -> s.getStatus() == Student.Status.HIBERNATING)
                .mapToLong(s -> s.getDob().until(LocalDate.now(), ChronoUnit.YEARS))
                .average().ifPresent(System.out::println);

//        if(averageAge.isPresent()){
//            double value = averageAge.getAsDouble();
//            System.out.println("double: " + value);
//        }

    }

    /*
    8.Write a method to return all the phone numbers of all customers. Make sure your test
data includes at least some customers with phone numbers.
     */
    @Test
    public void lab8() {
        StudentService ss = new StudentService();
        RegistrationApp.init(ss);

        List<Student> students = ss.getAllStudents();

        var result = students.stream()
                .flatMap(s -> s.getPhoneNumbers().stream())
                .collect(Collectors.toSet());
        System.out.println("result: " + result);

        var csvResult = students.stream()
                .flatMap(s -> s.getPhoneNumbers().stream())
                .collect(Collectors.joining(", "));

        System.out.println("csvResult: " + csvResult);


    }

    /*
    9.Write a method to return only the first phone number, if any, for all customers. For
your test data, make sure that some of your customers have multiple phone numbers,
and at least one customer has no phone numbers.
     */
    @Test
    public void lab9() {
        StudentService ss = new StudentService();
        RegistrationApp.init(ss);

        List<Student> students = ss.getAllStudents();

//        var result = students.stream()
//                .filter(s -> s.getPhoneNumbers().size() > 0)
//                .map(s -> s.getPhoneNumbers().get(0))

        var result = students.stream()
                .map(s -> s.getPhoneNumbers().stream().findFirst())
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        var rbyhand = new ArrayList<>();
        for(Student s : students) {
            Set<String> pns = s.getPhoneNumbers();
            Iterator<String> it = pns.iterator();
            while(it.hasNext()) {
                rbyhand.add(it.next());
                break;
            }
        }

        var result2 = students.stream()
                .flatMap(s -> s.getPhoneNumbers().stream().findFirst().stream())
                .collect(Collectors.toList());

        System.out.println("result: " + result);
        System.out.println("result2: " + result2);

    }

}
