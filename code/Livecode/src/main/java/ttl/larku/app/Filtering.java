package ttl.larku.app;

import ttl.larku.domain.Student;
import ttl.larku.service.StudentService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author whynot
 */
public class Filtering {

    private StudentService service = new StudentService();

    public static void main(String[] args) {
        Filtering app = new Filtering();
        init(app.service);

//        app.filter1();
        //app.filter2();
        app.filter3();

    }

    public void filter1() {
        List<Student> students = service.getAllStudents();

//        List<Student> result = studentsWithM(students, "M");

        List<Student> result = studentsWithM(students, "C");

        for (Student s : result) {
            System.out.println(s);
        }

    }

    public void filter2() {
        List<Student> students = service.getAllStudents();

        NameChecker nc = new NameChecker();
        Checker c1 = new Checker() {
            @Override
            public boolean check(Student s) {
                return s.getName().startsWith("M");
            }
        };

        Checker c2 = (s) -> s.getName().startsWith("M");

        Checker dobChecker = (s) -> s.getDob().until(LocalDate.now(), ChronoUnit.YEARS) > 20;

        //List<Student> result = soSoChecker(students, nc);
        //List<Student> result = soSoChecker(students, dobChecker);
        List<Student> result = soSoChecker(students, (s) -> s.getDob().until(LocalDate.now(), ChronoUnit.YEARS) > 20);

        for (Student s : result) {
            System.out.println(s);
        }

    }

    public void filter3() {
        List<Student> students = service.getAllStudents();

        List<Student> result = soSoChecker(students, (s) -> s.getDob().until(LocalDate.now(), ChronoUnit.YEARS) > 20);

//        for (Student s : result) {
//            System.out.println(s);
//        }

        List<Student> olderStudents = betterChecker(students, (s) -> s.getDob().until(LocalDate.now(), ChronoUnit.YEARS) > 20);
        for (Student s : result) {
            System.out.println(s);
        }

        List<String> lString = List.of("one", "two", "threeee");
        GenericChecker<String> gcString = s -> s.length() > 4;

        List<String> gt4 = betterChecker(lString, gcString);

        List<String> gt5 = betterChecker(lString, s -> s.length() > 4);

        List<String> gt6 = filter(lString, s -> s.length() > 4);

        for(String s : gt4) {
            System.out.println(s);
        }
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

    public interface GenericChecker<T>
    {
        public boolean check(T t);
    }

    public <T> List<T> betterChecker(List<T> input, GenericChecker<T> checker) {
        List<T> result = new ArrayList<>();
        for (T s : input) {
            if (checker.check(s)) {
                result.add(s);
            }
        }
        return result;
    }

    public interface Checker {
        public boolean check(Student s);
    }

    public List<Student> soSoChecker(List<Student> input, Checker checker) {
        List<Student> result = new ArrayList<>();
        for (Student s : input) {
            if (checker.check(s)) {
                result.add(s);
            }
        }
        return result;
    }

    public List<Student> studentsOlderThan(List<Student> input, int limit) {
        LocalDate now = LocalDate.now();
        List<Student> result = new ArrayList<>();
        for (Student s : input) {
            if (s.getDob().until(now, ChronoUnit.YEARS) > limit) {
                result.add(s);
            }
        }

        return result;
    }

    public List<Student> studentsWithM(List<Student> input, String prefix) {
        List<Student> result = new ArrayList<>();
        for (Student s : input) {
            if (s.getName().startsWith(prefix)) {
                result.add(s);
            }
        }

        return result;
    }

    class NameChecker implements Checker {
        @Override
        public boolean check(Student s) {
            return s.getName().startsWith("M");
        }
    }

    public static void init(StudentService ss) {

        ss.insertStudent("Manoj", LocalDate.of(1988, 10, 2), Student.Status.FULL_TIME, "282 939 9944");
        ss.insertStudent("Charlene", LocalDate.of(1999, 8, 14), Student.Status.FULL_TIME, "282 898 2145", "298 75 83833");
        ss.insertStudent("Firoze", LocalDate.of(2002, 5, 2), Student.Status.HIBERNATING, "228 678 8765", "220 8795 26795");
        ss.insertStudent("Joe", LocalDate.of(1948, 9, 26), Student.Status.PART_TIME, "3838 678 3838");
    }
}
