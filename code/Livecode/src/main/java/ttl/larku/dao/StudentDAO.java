package ttl.larku.dao;

import ttl.larku.domain.Student;

import java.util.List;

/**
 * @author whynot
 */
public interface StudentDAO {
    Student insert(Student s);

    Student get(int id);

    List<Student> getAll();
}
