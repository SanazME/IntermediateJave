package ttl.larku.dao;

import ttl.larku.domain.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author whynot
 */
public class StudentDAO {

//    private List<Student> students = new ArrayList<>();
    private Map<Integer, Student> students = new HashMap<>();

    private List<Student> stuff = new ArrayList<>();

    public StudentDAO() {
        stuff.add(new Student(10, "one", LocalDate.now()));
    }

    public Student insert(Student s) {
        //TODO - generate ID

        students.put(s.getId(), s);

        return s;
    }

    public Student get(int id) {
        return students.get(id);
    }

    public Map<Integer, Student> getAll() {
        Map<Integer, Student> copy = new HashMap<>(students);

        return copy;
    }

    public List<Student> getStuff() {
        //return new ArrayList<>(stuff);
        return Collections.unmodifiableList(stuff);
    }

}
