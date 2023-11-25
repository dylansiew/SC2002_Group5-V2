package Camp;

import java.io.Serializable;
import java.util.ArrayList;

import Users.*;

public class CampParticipantManager implements Serializable {
    private CampAttendee campAttendee;
    private CampCommittee campCommittee;

    public CampParticipantManager(int committeeSize, int totalSize) {
        this.campAttendee = new CampAttendee(totalSize);
        this.campCommittee = new CampCommittee(committeeSize);
    }

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

    public CampParticipant getCampParticipant(Roles.ROLES role) {
        if (role == Roles.ROLES.ATTENDEE) {
            return this.campAttendee;
        } else if (role == Roles.ROLES.COMMITTEE) {
            return this.campCommittee;
        }
        return null;
    }

    @Override
    public String toString() {
        return "Remaining committee slots: " + this.getRemainingSlots(Roles.ROLES.COMMITTEE)
                + "\nRemaining total slots: " +
                this.getRemainingSlots(Roles.ROLES.STUDENT);
    }

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
