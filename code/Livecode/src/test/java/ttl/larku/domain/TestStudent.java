package ttl.larku.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author whynot
 */
public class TestStudent {

    @Test
    public void testAllParamsConstructor() {
        Student student = new Student(10, "Annie", LocalDate.of(2000, 5, 4), Set.of("383 939 8383"), Student.Status.FULL_TIME);
        Student student2 = new Student(10, "Annie", LocalDate.of(2000, 5, 4), Set.of(), Student.Status.FULL_TIME);

        assertEquals(Student.Status.FULL_TIME, student.getStatus());
        assertEquals(1, student.getPhoneNumbers().size());

    }

    @Test
    public void testAll3ParamsConstructor() {
//        Student student = new Student(10, "Annie", LocalDate.of(2000, 5, 4), Set.of("383 939 8383"), Student.Status.FULL_TIME);
        Student student = new Student(10, "Annie", LocalDate.of(2000, 5, 4));

        assertEquals(Student.Status.FULL_TIME, student.getStatus());
        assertEquals(0, student.getPhoneNumbers().size());

    }
}
