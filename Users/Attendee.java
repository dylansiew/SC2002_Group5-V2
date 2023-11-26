package Users;

import java.io.Serializable;
import java.util.*;
import Camp.*;
/**
 * The `Attendee` class represents an attendee in the system, associated with a student.
 */
public class Attendee implements Serializable {
    Student student;
    ArrayList<Camp> camp = new ArrayList<>();
    ArrayList<Camp> campBlacklist = new ArrayList<>();
    /**
     * Constructs a new `Attendee` instance associated with the specified student.
     * @param student The student associated with the attendee.
     */
    public Attendee(Student student) {
        this.student = student;
    }
    /**
     * Registers the attendee for a camp, adding it to the list of camps they are registered for.
     * If the camp is in the blacklist, registration will fail.
     * @param camp The camp to register for.
     */
    public void registerCamp(Camp camp) {
        if (campBlacklist.contains(camp)) {
            System.out.println("Unable to join camp you previously quit...");
            return;
        }
        this.camp.add(camp);
        Collections.sort(this.camp);
    }
    /**
     * Withdraws the attendee from a camp, removing it from the list of camps they are registered for.
     * The camp is also added to the blacklist to prevent future registration.
     * @param camp The camp to withdraw from.
     */
    public void withdrawCamp(Camp camp) {
        if (camp == null || !this.camp.contains(camp)) {
            return;
        }
        this.camp.remove(camp);
        this.campBlacklist.add(camp);

    }
    /**
     * Gets the list of camps that the attendee is registered for.
     * @return An ArrayList of camps.
     */
    public ArrayList<Camp> getCamps() {
        return this.camp;
    }
}
