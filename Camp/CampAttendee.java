package Camp;

import Users.Roles;

public class CampAttendee extends CampParticipant {

    public CampAttendee(int totalSlots) {
        super(Roles.ROLES.ATTENDEE, totalSlots);
    }
}
