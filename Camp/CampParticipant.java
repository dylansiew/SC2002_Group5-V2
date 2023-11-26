package Camp;

import java.io.Serializable;
import java.util.ArrayList;

import Users.*;
/**
 * The `CampParticipant` class represents a participant in a camp, which can include attendees, committee members, and more.
 * It provides methods to manage the participants and their roles within the camp.
 */
public abstract class CampParticipant implements Serializable {
    ArrayList<User> participantArrayList = new ArrayList<>();
    private Roles.ROLES role;
    private int totalSlots;
    /**
     * Constructs a new `CampParticipant` instance with the specified role and total slots available for participants.
     * @param role       The role of the camp participant.
     * @param totalSlots The total number of slots available for participants.
     */
    public CampParticipant(Roles.ROLES role, int totalSlots) {
        if (role == null) {
            return;
        }
        this.role = role;
        this.totalSlots = totalSlots;
    }
    /**
     * Adds a student as a participant to the camp if there are available slots.
     * @param user The user to add as a participant.
     * @return True if the user was successfully added, false otherwise.
     */
    public boolean addParticipant(User user) {
        if (user == null) {
            System.out.println("Error adding user");
            return false;
        }
        if (!(participantArrayList.contains(user)) && this.hasEmptySlots()) {
            participantArrayList.add(user);
            return true;
        } else {
            System.out.println("Error adding user");
            return false;
        }
    }
    /**
     * Removes a user from the list of camp participants.
     * @param user The user to remove from the camp participants.
     * @return True if the user was successfully removed, false otherwise.
     */
    public boolean removeParticipant(User user){
        if(user == null || !(participantArrayList.contains(user))){return false;
        }
        participantArrayList.remove(user);
        return true;
    }
    /**
     * Retrieves the list of participants in the camp.
     * @return An ArrayList containing the camp participants.
     */
    public ArrayList<User> getParticipantsArrayList() {
        return this.participantArrayList;
    }
    /**
     * Retrieves the total number of slots available for participants in the camp.
     * @return The total number of slots available.
     */
    public int getTotalSlot() {
        return this.totalSlots;
    }
    /**
     * Sets the total number of slots available for participants in the camp.
     * @param totalSlots The new total number of slots available.
     */
    public void setTotalSlot(int totalSlots) {
        if (totalSlots < 0) {
            System.out.println("Slots cannot be negative, failed to update.");
            return;
        }
        this.totalSlots = totalSlots;
    }
    /**
     * Retrieves the number of participants currently in the camp.
     * @return The number of camp participants.
     */
    public int getParticipantSize() {
        return this.participantArrayList.size();
    }
    /**
     * Checks if there are available slots for participants in the camp.
     * @return True if there are available slots, false otherwise.
     */
    public boolean hasEmptySlots() {
        return this.participantArrayList.size() < totalSlots;
    }
    /**
     * Returns a string representation of the camp participant information.
     * @return A string containing the participant's role and details of camp participants.
     */
    @Override
    public String toString() {
        int i = 1;
        String info = "\n" + role + " (" + participantArrayList.size() + "):\n";
        for (User user : participantArrayList) {
            info += i + ":\t" + user.toString() + "\n";
            i += 1;
        }
        return info;
    }
}
