package ttl.larku.app;

import ttl.larku.domain.Student;
import ttl.larku.service.StudentService;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author whynot
 */
public class SortingApp {

    private StudentService service = new StudentService();

    public static void main(String[] args) {
        SortingApp app = new SortingApp();
        init(app.service);

        //app.go();
//        app.goComparator();
        app.goLambda1();

    }

    public void go() {

        List<Student> students = service.getAllStudents();

        Collections.sort(students);

        for (Student s : students) {
            System.out.println(s);
        }
    }

    public void goComparator() {

        List<Student> students = service.getAllStudents();

        NameComparator nc = new NameComparator();
        DobComparator dc = new DobComparator();

        Collections.sort(students, dc);
//        students.sort(dc);

        for (Student s : students) {
            System.out.println(s);
        }
    }

    public void goLambda1() {

        List<Student> students = service.getAllStudents();

        NameComparator nc = new NameComparator();

        Comparator<Student> comp1 = new Comparator<>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };

        Comparator<Student> lambda1 = (Student o1, Student o2) -> {
            return o1.getName().compareTo(o2.getName());
        };

        Comparator<Student> lambda2 = (o1, o2) -> {
            return o1.getName().compareTo(o2.getName());
        };

        Comparator<Student> lambda3 = (o1, o2) -> o1.getName().compareTo(o2.getName());


        //Collections.sort(students, lambda3);

        //Collections.sort(students, (o1, o2) -> o1.getName().compareTo(o2.getName()));
        Collections.sort(students, (o1, o2) -> o1.getDob().compareTo(o2.getDob()));
//        students.sort(dc);

        for (Student s : students) {
            System.out.println(s);
        }
    }


    class NameComparator implements Comparator<Student> {
        @Override
        public int compare(Student student1, Student student2) {
            return student1.getName().compareTo(student2.getName());
        }
    }

    class DobComparator implements Comparator<Student> {
        @Override
        public int compare(Student student1, Student student2) {
            return student1.getDob().compareTo(student2.getDob());
        }
    }

    public void doSomething(List<String> s) {
    }

    public static <T extends Comparable<T>> void sort(List<T> list) {
    }

    public static <T> void sort(List<T> list, Comparator<T> c) {
    }

    public static void init(StudentService ss) {

        ss.insertStudent("Manoj", LocalDate.of(1988, 10, 2), Student.Status.FULL_TIME, "282 939 9944");
        ss.insertStudent("Charlene", LocalDate.of(1999, 8, 14), Student.Status.FULL_TIME, "282 898 2145", "298 75 83833");
        ss.insertStudent("Firoze", LocalDate.of(2002, 5, 2), Student.Status.HIBERNATING, "228 678 8765", "220 8795 26795");
        ss.insertStudent("Joe", LocalDate.of(1948, 9, 26), Student.Status.PART_TIME, "3838 678 3838");
    }
}
