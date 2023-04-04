package ttl.larku.app;

import ttl.larku.domain.Student;
import ttl.larku.service.StudentService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author whynot
 */
public class Streams {

    private StudentService service = new StudentService();

    public static void main(String[] args) {
        Streams app = new Streams();
        init(app.service);

        app.stream1();
    }


    public void stream1() {
        List<Student> students = service.getAllStudents();

        List<String> namesOver36 = students.stream()
                .peek(s -> System.out.println("In Peek 1 with " + s))
                .filter(s -> s.getDob().until(LocalDate.now(), ChronoUnit.YEARS) > 36)
                .peek(s -> System.out.println("In Peek 2 with " + s))
                .map(s -> s.getName())
                .peek(s -> System.out.println("In Peek 3 with " + s))
                .collect(Collectors.toList());

        namesOver36.forEach(s -> System.out.println(s));

//        for(String s : namesOver36) {
//            System.out.println(s);
//        }

//        students.stream()
//                .peek(s -> System.out.println("In Peek 1 with " + s))
//                .filter(s -> s.getDob().until(LocalDate.now(), ChronoUnit.YEARS) > 36)
//                .peek(s -> System.out.println("In Peek 2 with " + s))
//                .map(s -> s.getName())
//                .peek(s -> System.out.println("In Peek 3 with " + s));


    }
    public void map4() {
        List<Student> students = service.getAllStudents();

        List<Student> over36 = filter(students, s -> s.getDob().until(LocalDate.now(), ChronoUnit.YEARS) > 36);
        List<String> lStr = map(over36, s -> s.getName());

        List<String> lStr2 = map(filter(students, s -> s.getDob().until(LocalDate.now(), ChronoUnit.YEARS) > 36), s -> s.getName());

        for (String s : lStr) {
            System.out.println(s);
        }
    }

    public <T, R> List<R> map(List<T> input, Function<T, R> extractor) {
        List<R> result = new ArrayList<>();
        for(T s : input) {
            result.add(extractor.apply(s));
        }

        return result;
    }

    public <T> List<T> filter(List<T> input, Predicate<T> checker) {
        List<T> result = new ArrayList<>();
        for (T s : input) {
            if (checker.test(s)) {
                result.add(s);
            }
        }
        return result;
    }

    interface GenericExtractor<T, R> {
        public R extract(T t);
    }

    public <T, R> List<R> almostBestExtractor(List<T> input, GenericExtractor<T, R> extractor) {
        List<R> result = new ArrayList<>();
        for(T s : input) {
            result.add(extractor.extract(s));
        }

        return result;
    }

    interface Extractor {
        public String extract(Student s);
    }

    public List<String> betterExtractor(List<Student> input, Extractor extractor) {
        List<String> result = new ArrayList<>();
        for(Student s : input) {
            result.add(extractor.extract(s));
        }

        return result;
    }

    class NameExtractor implements Extractor
    {
        @Override
        public String extract(Student s) {
            return s.getName();
        }
    }

    public List<String> simpleMindedExtract(List<Student> input) {
        List<String> result = new ArrayList<>();
        for(Student s : input) {
            result.add(s.getName());
        }

        return result;
    }

    public static void init(StudentService ss) {

        ss.insertStudent("Manoj", LocalDate.of(1988, 10, 2), Student.Status.FULL_TIME);
        ss.insertStudent("Charlene", LocalDate.of(1999, 8, 14), Student.Status.FULL_TIME, "282 898 2145", "298 75 83833");
        ss.insertStudent("Firoze", LocalDate.of(2002, 5, 2), Student.Status.HIBERNATING, "228 678 8765", "220 8795 26795");
        ss.insertStudent("Joe", LocalDate.of(1948, 9, 26), Student.Status.PART_TIME, "3838 678 3838");
    }
}
