package ttl.larku.dao;

import org.junit.jupiter.api.Test;
import ttl.larku.domain.Student;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * @author whynot
 */
public class TestStudentDAO {

    @Test
    public void testGetAll() {

        Student s1 = new Student(1, "Joe", LocalDate.of(1978, 10, 10));

        StudentDAO dao = new StudentDAO();
        Student insertedStudent = dao.insert(s1);

        Map<Integer, Student> allStudents = dao.getAll();

        assertEquals(1, allStudents.size());

        Student s2 = new Student(1, "Rosy", LocalDate.of(1978, 10, 10));
        allStudents.put(10, s2);

        Student s3 = dao.get(1);
        assertEquals("Joe", s3.getName());

    }

    @Test
    public void testListReturn() {
        StudentDAO dao = new StudentDAO();

        List<Student> l1 = dao.getStuff();

//        l1.get(0).setName("XXXXXXXXX");

        assertEquals("one", l1.get(0).getName());

        Student s2 = new Student(1, "boo", LocalDate.of(1978, 10, 10));
        l1.remove(0);
        l1.add(s2);
        assertEquals("boo", l1.get(0).getName());

        Student s3 = dao.get(10);
        assertEquals("one", l1.get(0).getName());
    }
}
