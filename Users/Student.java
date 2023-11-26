package Users;

import java.util.*;
import Camp.*;
/**
 * The `Student` class represents a student user in the system.
 * Students have the ability to register for and manage their participation in camps.
 */
public class Student extends User {
    Committee studentCommittee;
    Attendee studentAttendee;
    BusyDate busyDate = new BusyDate();
     /**
     * Creates a new student with the specified name, email, and faculty.
     * @param name    The name of the student.
     * @param email   The email of the student.
     * @param faculty The faculty to which the student belongs.
     */
    public Student(String name, String email, Faculty.FACULTY_TYPE faculty) {
        super(name, email, faculty);
        this.id = User.nextStudentId;
        User.nextStudentId += 1;
    }
    /**
     * Registers the student for a camp, either as an attendee or committee member.
     * @param camp     The camp to be registered for.
     * @param attendee `true` if registering as an attendee, `false` for committee member.
     * @return `true` if registration is successful, `false` otherwise.
     */
    public boolean registerCamp(Camp camp, boolean attendee) {
        if (camp == null) {
            return false;
        }
        CampInformation campInformation = camp.getCampInformation();
        if (campInformation == null) {
            return false;
        }
        if (camp.getUserManager().getParticipantManager().getRemainingSlots(Roles.ROLES.STUDENT) <= 0) {
            System.out.println("Camp is full!");
            return false;
        }
        if (busyDate != null && busyDate.isOverlap(campInformation.getCampDate().getDates())) {
            System.out.println("There's an overlap in your schedule, registration failed!");
            return false;
        }
        if (attendee) {
            if (this.studentAttendee != null && this.studentAttendee.campBlacklist.contains(camp)) {
                System.out.println("Unable to join camp you previously quit...");
                return false;
            }
            if (!(camp.getUserManager().getParticipantManager().getCampParticipant(Roles.ROLES.ATTENDEE)
                    .addParticipant(this))) {
                return false;
            }
            if (studentAttendee == null) {
                this.studentAttendee = new Attendee(this);
            }
            this.studentAttendee.registerCamp(camp);
        } else {
            if (!(camp.getUserManager().getParticipantManager().getCampParticipant(Roles.ROLES.COMMITTEE)
                    .addParticipant(this))) {
                return false;
            }
            this.studentCommittee = new Committee(camp, this);
        }
        if (busyDate.addDates(campInformation.getCampDate().getDates())) {
            System.out.printf("Successfully joined as %s\n", attendee ? "attendee" : "committee member");
            return true;
        }
        return false;
    }
    /**
     * Withdraws the student from an attendee camp.
     * @param camp The camp to withdraw from.
     */
    public void withdrawAttendeeCamp(Camp camp) {
        if (camp == null) {
            return;
        }
        Date[] eventDates = camp.getCampInformation().getCampDate().getDates();
        busyDate.removeDates(eventDates);
        camp.getUserManager().getParticipantManager().getCampParticipant(Roles.ROLES.ATTENDEE).removeParticipant(this);
        studentAttendee.withdrawCamp(camp);
    }

    public Attendee getAttendee() {
        return this.studentAttendee;
    }

    public Committee getCommittee() {
        return this.studentCommittee;
    }

    public BusyDate getBusyDate() {
        return this.busyDate;
    }

    public String toString() {
        return "User: Student " + super.toString();
    }
}
