package Users;

import java.util.*;
import Camp.*;

public class Student extends User {
    Committee studentCommittee;
    Attendee studentAttendee;
    BusyDate busyDate = new BusyDate();

    public Student(String name, String email, Faculty.FACULTY_TYPE faculty) {
        super(name, email, faculty);
        this.id = User.nextStudentId;
        User.nextStudentId += 1;
    }

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
