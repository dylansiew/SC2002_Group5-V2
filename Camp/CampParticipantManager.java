package Camp;

import java.io.Serializable;
import java.util.ArrayList;

import Users.*;
/**
 * The `CampParticipantManager` class manages camp participants, including attendees and committee members.
 * It provides methods to track the number of participants, available slots, and retrieve participant lists.
 */
public class CampParticipantManager implements Serializable {
    private CampAttendee campAttendee;
    private CampCommittee campCommittee;
    /**
     * Constructs a new `CampParticipantManager` instance with the specified committee size and total size.
     * @param committeeSize The size of the committee (committee members).
     * @param totalSize     The total size of participants.
     */
    public CampParticipantManager(int committeeSize, int totalSize) {
        this.campAttendee = new CampAttendee(totalSize);
        this.campCommittee = new CampCommittee(committeeSize);
    }
    /**
     * Retrieves the remaining available slots for a given role.
     * @param role The role for which to check available slots.
     * @return The number of remaining available slots, or -1 if the role is unrecognized.
     */
    public int getRemainingSlots(Roles.ROLES role) {
        if (role == null) {
            return -1;
        }
        if (role == Roles.ROLES.ATTENDEE || role == Roles.ROLES.STUDENT) {
            return campAttendee.getTotalSlot() - this.getCurrentSize(Roles.ROLES.STUDENT);
        } else if (role == Roles.ROLES.COMMITTEE) {
            return campCommittee.getTotalSlot() - this.getCurrentSize(Roles.ROLES.COMMITTEE);
        } else {
            System.out.println("Unrecoggnised role");
            return -1;
        }
    }
    /**
     * Retrieves the current number of participants for a given role.
     * @param role The role for which to get the participant count.
     * @return The number of participants for the specified role, or -1 if the role is unrecognized.
     */
    public int getCurrentSize(Roles.ROLES role) {
        if (role == Roles.ROLES.ATTENDEE) {
            return this.campAttendee.getParticipantSize();
        } else if (role == Roles.ROLES.COMMITTEE) {
            return this.campCommittee.getParticipantSize();
        } else if (role == Roles.ROLES.STUDENT) {
            return this.campAttendee.getParticipantSize() + this.campCommittee.getParticipantSize();
        } else {
            System.out.println("Unrecoggnised role");
            return -1;
        }
    }
    /**
     * Retrieves the list of participants for a given role.
     * @param role The role for which to retrieve the participant list.
     * @return An ArrayList containing the participants for the specified role, or null if the role is unrecognized.
     */
    public ArrayList<User> getParticipantsArrayList(Roles.ROLES role) {
        if (role == null) {
            System.out.println("Error getting users");
            return null;
        }
        if (role == Roles.ROLES.ATTENDEE) {
            return this.campAttendee.getParticipantsArrayList();
        } else if (role == Roles.ROLES.COMMITTEE) {
            return this.campCommittee.getParticipantsArrayList();
        } else if (role == Roles.ROLES.STUDENT) {
            ArrayList<User> combinedList = new ArrayList<>();
            combinedList.addAll(campAttendee.getParticipantsArrayList());
            combinedList.addAll(campCommittee.getParticipantsArrayList());

            return combinedList;
        } else {
            return null;
        }
    }
    /**
     * Retrieves the camp participant object for a given role.
     * @param role The role for which to retrieve the camp participant object.
     * @return The camp participant object for the specified role, or null if the role is unrecognized.
     */
    public CampParticipant getCampParticipant(Roles.ROLES role) {
        if (role == Roles.ROLES.ATTENDEE) {
            return this.campAttendee;
        } else if (role == Roles.ROLES.COMMITTEE) {
            return this.campCommittee;
        }
        return null;
    }
    /**
     * Returns a string representation of the remaining committee slots and total slots for students.
     * @return A string with information about remaining slots.
     */
    @Override
    public String toString(){
        return "Remaining committee slots: " + this.getRemainingSlots(Roles.ROLES.COMMITTEE)
                + "\nRemaining total slots: "+
                this.getRemainingSlots(Roles.ROLES.STUDENT);
    }
    /**
     * Returns a string representation of the participants for a given role or all roles.
     * @param role The role for which to retrieve the participant information.
     * @return A string containing the participant details, or null if the role is unrecognized.
     */
    public String toString(Roles.ROLES role) {
        if (role == Roles.ROLES.ATTENDEE) {
            return this.campAttendee.toString();
        } else if (role == Roles.ROLES.COMMITTEE) {
            return this.campCommittee.toString();
        } else if (role == Roles.ROLES.STUDENT) {
            return campAttendee.toString() + campCommittee.toString();
        }
        return null;

    }

}
