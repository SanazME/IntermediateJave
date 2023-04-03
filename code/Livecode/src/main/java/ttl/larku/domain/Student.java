package ttl.larku.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
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

    /*
   An integer id
2. A name
3. A dateOfBirth of type LocalDate
1. You can initialize a LocalDate with the of method, e.g. LocalDate.of(1957, 10, 10)
4. Zero or more phone numbers
5. A status, which can have the following values:
• Privileged
• Normal
• Restricted
     */

    private int id;
    private String name;

    private LocalDate dob;

//    private List<String> phoneNumbers;
    private Set<String> phoneNumbers;
    private Status status;

    public Student(int id, String name, LocalDate dob) {
        this(id, name, dob, new HashSet<>(), Status.FULL_TIME);
//        init(id, name, dob, new HashSet<>(), Status.FULL_TIME);
//        this.id = id;
//        this.name = name;
//        this.dob = dob;
//
//        this.phoneNumbers = new HashSet<>();
//        this.status = Status.FULL_TIME;
    }

    public Student(int id, String name, LocalDate dob, Set<String> phoneNumbers, Status status) {
//        init(id, name, dob, phoneNumbers, status);
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.phoneNumbers = phoneNumbers;
        this.status = status;
    }

    public void init(int id, String name, LocalDate dob, Set<String> phoneNumbers, Status status) {
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

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Set<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(Set<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
