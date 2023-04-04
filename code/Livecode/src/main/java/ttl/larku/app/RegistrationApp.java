package ttl.larku.app;

import ttl.larku.dao.InMemoryStudentDAO;
import ttl.larku.domain.Student;
import ttl.larku.service.StudentService;

import java.time.LocalDate;
import java.util.List;

/**
 * @author whynot
 */
public class RegistrationApp {

    private StudentService service = new StudentService();

    public static void main(String[] args) {
        RegistrationApp app = new RegistrationApp();

        init(app.service);

        app.postStudents();

        app.getStudents();
    }

    public void postStudents() {
//        StudentService service = new StudentService();
//        Student s2 = new Student("Rosy", LocalDate.of(1978, 10, 10));
        Student s3 = new Student("Joey", LocalDate.of(1978, 10, 10), Student.Status.HIBERNATING, "282 828 92929");

//        service.insertStudent(s2);
        service.insertStudent(s3);

        List<Student> students = service.getAllStudents();

        System.out.println("From Post");
        for(Student student: students) {
            System.out.println(student);
        }
    }

    public void getStudents() {
//        StudentService service = new StudentService();

        List<Student> students = service.getAllStudents();

        System.out.println("From Get");
        for(Student student: students) {
            System.out.println(student);
        }
    }

    public static void init(StudentService ss) {

        ss.insertStudent("Manoj", LocalDate.of(1988, 10, 2), Student.Status.FULL_TIME);
        ss.insertStudent("Charlene", LocalDate.of(1999, 8, 14), Student.Status.FULL_TIME, "282 898 2145", "298 75 83833");
        ss.insertStudent("Firoze", LocalDate.of(2002, 5, 2), Student.Status.HIBERNATING, "383 838 8383", "228 678 8765", "220 8795 26795");
        ss.insertStudent("Joe", LocalDate.of(1948, 9, 26), Student.Status.PART_TIME, "3838 678 3838");
    }
}
