package ttl.larku.domain;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author whynot
 */
public class Student {

    enum Status {
        FULL_TIME,
        PART_TIME,
        HIBERNATING
    }


    private int id;
    private String name;

    private LocalDate dob;

//    private List<String> phoneNumbers;
    private Set<String> phoneNumbers;
    private Status status;

    public Student() {}

    public Student(int id, String name, LocalDate dob) {
        this(id, name, dob, Status.FULL_TIME, new HashSet<>());
//        init(id, name, dob, new HashSet<>(), Status.FULL_TIME);
//        this.id = id;
//        this.name = name;
//        this.dob = dob;
//
//        this.phoneNumbers = new HashSet<>();
//        this.status = Status.FULL_TIME;
    }

    public Student(int id, String name, LocalDate dob, Status status, String ... phoneNumbers) {
        //this(id, name, dob, Status.FULL_TIME, Set.of(phoneNumbers));
//        this(id, name, dob, Status.FULL_TIME, Collections.addAll(this.phoneNumbers = new HashSet<>(), phoneNumbers));

        this.phoneNumbers = new HashSet<>();
        Collections.addAll(new HashSet<>(), phoneNumbers);
        init(id, name, dob, status, this.phoneNumbers);
    }

    public Student(int id, String name, LocalDate dob, Status status, Set<String> phoneNumbers) {
        init(id, name, dob, status, phoneNumbers);
//        this.id = id;
//        this.name = name;
//        this.dob = dob;
//        this.phoneNumbers = phoneNumbers;
//        this.status = status;
    }

    private void init(int id, String name, LocalDate dob, Status status, Set<String> phoneNumbers) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.phoneNumbers = phoneNumbers;
        this.status = status;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

//    public void setName(String name) {
//        this.name = name;
//    }

    public LocalDate getDob() {
        return dob;
    }

//    public void setDob(LocalDate dob) {
//        this.dob = dob;
//    }

    public Set<String> getPhoneNumbers() {
        return phoneNumbers;
    }

//    public void setPhoneNumbers(Set<String> phoneNumbers) {
//        this.phoneNumbers = phoneNumbers;
//    }

    public Status getStatus() {
        return status;
    }

//    public void setStatus(Status status) {
//        this.status = status;
//    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", phoneNumbers=" + phoneNumbers +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
