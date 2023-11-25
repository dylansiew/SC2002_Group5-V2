package Users;

public class AdminUser extends User {
    public AdminUser(String name, String email, Faculty.FACULTY_TYPE faculty) {
        super(name, email, faculty);
    }

    public String toString() {
        return "User: Admin " + super.toString();
    }
}
