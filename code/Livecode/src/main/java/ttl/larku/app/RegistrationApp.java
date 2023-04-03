package ttl.larku.app;

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
}
