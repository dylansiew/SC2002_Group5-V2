package Camp;

import java.io.Serializable;

import Users.*;
/**
 * The `CampUserManager` class is responsible for managing users and participants in a camp.
 * It contains a `CampParticipantManager` to handle participants and a `CampOrganiserManager` for camp organizers.
 */
public class CampUserManager implements Serializable {
    private CampParticipantManager campParticipantManager;
    private CampOrganiserManager campOrganiserManager;
    /**
     * Constructs a new `CampUserManager` instance with the specified user, committee size, and total size.
     * @param user          The user who will be the organizer of the camp.
     * @param committeeSize The size of the camp committee.
     * @param totalSize     The total size of participants allowed in the camp.
     */
    public CampUserManager(User user, int committeeSize, int totalSize) {
        campParticipantManager = new CampParticipantManager(committeeSize, totalSize);
        campOrganiserManager = new CampOrganiserManager(user);

    }
    /**
     * Gets the `CampParticipantManager` responsible for managing camp participants.
     * @return The `CampParticipantManager` instance.
     */
    public CampParticipantManager getParticipantManager() {
        return this.campParticipantManager;
    }
    /**
     * Gets the `CampOrganiserManager` responsible for managing camp organizers.
     *
     * @return The `CampOrganiserManager` instance.
     */
    public CampOrganiserManager getCampOrganiserManager() {
        return this.campOrganiserManager;
    }
    /**
     * Returns a string representation of the `CampUserManager` containing information about camp organizers and participants.
     *
     * @return A string representation of the `CampUserManager`.
     */
    @Override
    public String toString() {
        return campOrganiserManager.toString() + campParticipantManager.toString();
    }
}
