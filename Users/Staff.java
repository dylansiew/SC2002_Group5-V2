package Users;

import java.util.*;
import Camp.*;
/**
 * The `Staff` class represents a staff member user in the system.
 * Staff members have the ability to create and manage camps.
 */
public class Staff extends User {
    private ArrayList<Camp> campsCreated = new ArrayList<Camp>();
    /**
     * Creates a new staff member with the specified name, email, and faculty.
     * @param name    The name of the staff member.
     * @param email   The email of the staff member.
     * @param faculty The faculty to which the staff member belongs.
     */
    public Staff(String name, String email, Faculty.FACULTY_TYPE faculty) {
        super(name, email, faculty);
        this.id = User.nextStaffId;
        User.nextStaffId += 1;
    }
    /**
     * Adds a camp created by the staff member to their list of created camps.
     * @param camp The camp to be added.
     */
    public void addCamp(Camp camp) {
        campsCreated.add(camp);
        Collections.sort(campsCreated);
    }
    /**
     * Gets the list of camps created by the staff member.
     * @return An ArrayList of camps created by the staff member.
     */
    public ArrayList<Camp> getCampsCreated() {
        return this.campsCreated;
    }
    /**
     * Returns a string representation of the staff member.
\     * @return A string representation of the staff member.
     */
    public String toString() {
        return "User: Staff " + super.toString();
    }
    /**
     * Removes a camp from the list of camps created by the staff member.
     * @param camp The camp to be removed.
     */
    public void removeCamp(Camp camp) {
        if (camp == null || !campsCreated.contains(camp)) {
            return;
        }
        campsCreated.remove(camp);
    }
}
