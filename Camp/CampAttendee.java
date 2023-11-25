package Camp;

import Users.Roles;
/**
 * The `CampAttendee` class represents an attendee participant in a camp, inheriting from `CampParticipant`.
 * It is associated with the role of an attendee and manages the total slots available for attendees.
 */
public class CampAttendee extends CampParticipant{
    /**
     * Constructs a new `CampAttendee` instance and specified total slots available for attendees.
     * @param totalSlots The total number of slots available for attendees in the camp.
     */
    public CampAttendee(int totalSlots) {
        super(Roles.ROLES.ATTENDEE, totalSlots);
    }
}
