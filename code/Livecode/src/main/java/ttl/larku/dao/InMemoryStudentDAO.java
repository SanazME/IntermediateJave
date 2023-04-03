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
public class InMemoryStudentDAO implements StudentDAO {

    //    private List<Student> students = new ArrayList<>();
    private Map<Integer, Student> students = new HashMap<>();

    private int nextId = 1;

    private List<Student> stuff = new ArrayList<>();

    public InMemoryStudentDAO() {
        stuff.add(new Student("one", LocalDate.now()));
    }

    @Override
    public Student insert(Student s) {
        int id = nextId++;
        //TODO - generate ID
        s.setName("InMem:" + s.getName());
        s.setId(id);
        students.put(s.getId(), s);

        return s;
    }

    @Override
    public Student get(int id) {
        return students.get(id);
    }

    @Override
    public List<Student> getAll() {
        return List.copyOf(students.values());
    }
    //    public Map<Integer, Student> getAll() {
    public Map<Integer, Student> oldGetAll() {
        Map<Integer, Student> copy = new HashMap<>(students);

        return copy;
    }

    public List<Student> getStuff() {
        //return new ArrayList<>(stuff);
        return Collections.unmodifiableList(stuff);
    }
}
