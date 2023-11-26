package Users;
/**
 * The `AdminUser` class represents an administrative user in the system. Its purpose is to add Student and Staff
 */
public class AdminUser extends User {
    /**
     * Constructs a new `AdminUser` instance with the specified name, email, and faculty type.
     * @param name     The name of the admin user.
     * @param email    The email address of the admin user.
     * @param faculty  The faculty type associated with the admin user.
     */
    public AdminUser(String name, String email, Faculty.FACULTY_TYPE faculty) {
        super(name, email, faculty);
    }
    /**
     * Returns a string representation of the admin user.
     * @return A string containing the user type ("Admin") and user information.
     */
    public String toString() {
        return "User: Admin " + super.toString();
    }
}
