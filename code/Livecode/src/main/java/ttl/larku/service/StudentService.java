package ttl.larku.service;

import ttl.larku.dao.DAOFactory;
import ttl.larku.dao.InMemoryStudentDAO;
import ttl.larku.dao.MysqlStudentDAO;
import ttl.larku.dao.StudentDAO;
import ttl.larku.domain.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author whynot
 */
public class StudentService {

    //List<String> list = new ArrayList<>();
    //List<String> list = new ArrayList<>();
    List<String> list = new LinkedList<>();

    //private InMemoryStudentDAO studentDAO = new InMemoryStudentDAO();
    //private MysqlStudentDAO studentDAO = new MysqlStudentDAO();
//    private StudentDAO studentDAO = new MysqlStudentDAO();
//    private StudentDAO studentDAO = new InMemoryStudentDAO();

    private StudentDAO studentDAO = DAOFactory.studentDAO();

    public Student insertStudent(String name, LocalDate dob, Student.Status status, String ... phoneNumbers) {
        return insertStudent(new Student(name, dob, status, phoneNumbers));

//        int newId = studentDAO.insert(student);
//        return studentDAO.get(newId);
    }
    public Student insertStudent(Student student) {
       return studentDAO.insert(student);

//        int newId = studentDAO.insert(student);
//        return studentDAO.get(newId);
    }

    public Student getStudent(int id) {
        return studentDAO.get(id);
    }

    public List<Student> getAllStudents() {
        return studentDAO.getAll();
    }

    public StudentDAO getStudentDAO() {
        return studentDAO;
    }
}
