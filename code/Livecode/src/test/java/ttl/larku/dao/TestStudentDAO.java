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

        Student s1 = new Student("Joe", LocalDate.of(1978, 10, 10));

        InMemoryStudentDAO dao = new InMemoryStudentDAO();
        Student insertedStudent = dao.insert(s1);

        Map<Integer, Student> allStudents = dao.oldGetAll();

        assertEquals(1, allStudents.size());

        Student s2 = new Student("Rosy", LocalDate.of(1978, 10, 10));
        allStudents.put(10, s2);

        Student s3 = dao.get(1);
        assertEquals("Joe", s3.getName());

    }

    @Test
    public void testListReturn() {
        InMemoryStudentDAO dao = new InMemoryStudentDAO();

        List<Student> l1 = dao.getStuff();

//        l1.get(0).setName("XXXXXXXXX");

        assertEquals("one", l1.get(0).getName());
    }

    @Test
    public void testIdGeneration() {
        InMemoryStudentDAO dao = new InMemoryStudentDAO();

        Student s2 = new Student("Rosy", LocalDate.of(1978, 10, 10));
        Student s3 = new Student("Joey", LocalDate.of(1978, 10, 10));

        Student insertedStudent = dao.insert(s2);
        Student insertedStudent2 = dao.insert(s3);

        assertEquals(1, insertedStudent.getId());

        assertEquals(2, insertedStudent2.getId());

    }
}
