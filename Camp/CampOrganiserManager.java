package Camp;

import java.io.Serializable;

import Users.*;
/**
 * The `CampOrganiserManager` class represents the manager responsible for organizing a camp, associated with an organizer user.
 */
public class CampOrganiserManager  implements Serializable{
    private User organiser;
    /**
     * Constructs a new `CampOrganiserManager` instance with the specified organizer user.
     * @param user The organizer user responsible for managing the camp.
     */
    public CampOrganiserManager(User user) {
        this.organiser = user;
    }
    /**
     * Retrieves the organizer user responsible for managing the camp.
     * @return The organizer user.
     */
    public User getOrganiser() {
        return this.organiser;
    }
    /**
     * Returns a string representation of the camp organizer's information.
     * @return A string containing the organizer's name.
     */
    @Override
    public String toString() {
        return "Organiser: " + organiser.getName() + "\n";
    }
}
