package ttl.larku.app;

import ttl.larku.domain.Student;
import ttl.larku.service.StudentService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author whynot
 */
public class FlatMapAndOptional {

    private StudentService service = new StudentService();

    public static void main(String[] args) {
        FlatMapAndOptional app = new FlatMapAndOptional();
        init(app.service);

        //app.stream1();

        app.optionalDouble(new int[] {});
    }

    public List<String> byHand(List<Student> input) {
        List<String> result = new ArrayList<>();
        for(Student s : input) {
            Set<String> pns = s.getPhoneNumbers();
            for(String pn : pns) {
                result.add(pn);
            }

        }
        return result;
    }

    public void stream1() {
        List<Student> students = service.getAllStudents();

        List<String> result = students.stream()
                .peek(s -> System.out.println("In Peek 1 with: " + s))
                .flatMap(s -> s.getPhoneNumbers().stream())
                .peek(s -> System.out.println("In Peek 2 with: " + s))
                .map(this::processPhoneNumber)
                //.map(this::processPhoneNumber)
                .collect(Collectors.toList());

//        result.forEach(s -> System.out.println(s));
//
//        result.forEach(System.out::println);

        result.forEach(this::prettyPrint);

    }

    public String processPhoneNumber(String phoneNumber) {
        //Lots of work over here
        return "Processed" + phoneNumber;
    }

    public void prettyPrint(String s) {
        System.out.println("[[ " + s + " ]]");
    }

    public void optionalDouble(int [] iarr) {

        var s1 = Arrays.stream(iarr)
                .map(i -> i * i)
                .sum();

        System.out.println("s1: " + s1);

        var s2 = Arrays.stream(iarr)
                .map(i -> i * i)
                .average();

        var doubleResult = s2.orElse(-1);

        Arrays.stream(iarr)
                .map(i -> i * i)
                .average().ifPresent(System.out::println);

        double result = Arrays.stream(iarr)
                .map(i -> i * i)
                .average().orElse(-1);

        List<Student> students = service.getAllStudents();

        var averageAge = students.stream()
                        .mapToLong(s -> s.getDob().until(LocalDate.now(), ChronoUnit.YEARS))
                                .average();





        System.out.println("s1: " + s1);

    }


    public void findFirst() {
        List<Student> students = service.getAllStudents();

        students.stream()
                .filter(s -> s.getStatus() == Student.Status.FULL_TIME)
                .findFirst().ifPresentOrElse(s -> System.out.println(),
                        () -> System.err.println("Nothing found"));

    }

    public static void init(StudentService ss) {

        ss.insertStudent("Manoj", LocalDate.of(1988, 10, 2), Student.Status.FULL_TIME);
        ss.insertStudent("Charlene", LocalDate.of(1999, 8, 14), Student.Status.FULL_TIME, "282 898 2145", "298 75 83833");
        ss.insertStudent("Firoze", LocalDate.of(2002, 5, 2), Student.Status.HIBERNATING, "228 678 8765", "220 8795 26795");
        ss.insertStudent("Joe", LocalDate.of(1948, 9, 26), Student.Status.PART_TIME, "3838 678 3838");
    }
}
