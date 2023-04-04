package ttl.larku.app;

import ttl.larku.domain.Student;
import ttl.larku.service.StudentService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author whynot
 */
public class Mapping {

    private StudentService service = new StudentService();

    public static void main(String[] args) {
        Mapping app = new Mapping();
        init(app.service);

//        app.map1();
//        app.map2();
//        app.map3();
        app.map4();
    }

    public void map1() {
        List<Student> students = service.getAllStudents();

        List<String> result = simpleMindedExtract(students);

        for (String s : result) {
            System.out.println(s);
        }
    }

    public void map2() {
        List<Student> students = service.getAllStudents();
        NameExtractor ne = new NameExtractor();

//        List<String> result = betterExtractor(students, ne);

        List<String> result2 = betterExtractor(students, s -> {
            return s.getName();
        });

        for (String s : result2) {
            System.out.println(s);
        }
    }

    public void map3() {
        List<Student> students = service.getAllStudents();

        GenericExtractor<Student, String> ge = s -> s.getName();

        GenericExtractor<String, Integer> lengthExtractor = s -> s.length();

        List<String> lStr = map(students, s -> s.getName());

        for (String s : lStr) {
            System.out.println(s);
        }

        List<Integer> dobs = map(students, s -> s.getDob().getYear());
        for (Integer d : dobs) {
            System.out.println(d);
        }

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

        ss.insertStudent("Manoj", LocalDate.of(1988, 10, 2), Student.Status.FULL_TIME, "282 939 9944");
        ss.insertStudent("Charlene", LocalDate.of(1999, 8, 14), Student.Status.FULL_TIME, "282 898 2145", "298 75 83833");
        ss.insertStudent("Firoze", LocalDate.of(2002, 5, 2), Student.Status.HIBERNATING, "228 678 8765", "220 8795 26795");
        ss.insertStudent("Joe", LocalDate.of(1948, 9, 26), Student.Status.PART_TIME, "3838 678 3838");
    }
}
