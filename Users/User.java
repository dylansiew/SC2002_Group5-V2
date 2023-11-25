package Users;

import java.io.Serializable;
/**
 * The `User` abstract class represents a user in the system.
 * It serves as a base class for different user types, such as students and staff.
 */
public abstract class User implements Serializable {
    private Authentication authentication;
    private String name;
    private String email;
    private Faculty.FACULTY_TYPE facultyType;
    int id;
    static int nextStaffId = 100;
    static int nextStudentId = 200;

    public User(String name, String email, Faculty.FACULTY_TYPE faculty) {
        this.name = name;
        this.email = email;
        this.facultyType = faculty;
        this.authentication = new Authentication(email.substring(0, email.indexOf("@")));
    }

    public Boolean equals(User anotherUser) {
        return this.id == anotherUser.id;
    }

    public Faculty.FACULTY_TYPE getFaculty() {
        return this.facultyType;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public Authentication getAuthentication() {
        return this.authentication;
    }

    public String toString() {
        return ("| ID: " + id + " | Name: " + name + " | Email: " + email + " | Faculty: " + facultyType);
    }
}
