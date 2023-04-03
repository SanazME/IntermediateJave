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
        Student student = new Student("Annie", LocalDate.of(2000, 5, 4), Student.Status.FULL_TIME, Set.of("383 939 8383"));
        Student student2 = new Student("Annie", LocalDate.of(2000, 5, 4), Student.Status.FULL_TIME, Set.of());

        Student student3 = new Student("Annie", LocalDate.of(2000, 5, 4), Student.Status.FULL_TIME);
        Student student4 = new Student("Annie", LocalDate.of(2000, 5, 4), Student.Status.FULL_TIME, "383 939 9393", "28980 0320 2");

        assertEquals(Student.Status.FULL_TIME, student.getStatus());

        assertEquals(1, student.getPhoneNumbers().size());

    }

    @Test
    public void testAll3ParamsConstructor() {
//        Student student = new Student(10, "Annie", LocalDate.of(2000, 5, 4), Set.of("383 939 8383"), Student.Status.FULL_TIME);
        Student student = new Student("Annie", LocalDate.of(2000, 5, 4));

        assertEquals(Student.Status.FULL_TIME, student.getStatus());
        assertEquals(0, student.getPhoneNumbers().size());

    }

    @Test
    public void testBarArgsConstructor() {
//        Student student = new Student(10, "Annie", LocalDate.of(2000, 5, 4), Set.of("383 939 8383"), Student.Status.FULL_TIME);
        Student student = new Student("Annie", LocalDate.of(2000, 5, 4), Student.Status.HIBERNATING);

        assertEquals(Student.Status.HIBERNATING, student.getStatus());
        assertEquals(0, student.getPhoneNumbers().size());
    }
}
