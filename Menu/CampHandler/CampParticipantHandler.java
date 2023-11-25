package Menu.CampHandler;

import Camp.Camp;
import Controller.AllCamp;
import Menu.FilterCamp;
import Program.MainProgram;
import Users.Roles.ROLES;
import Users.Student;
import Users.User;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class CampParticipantHandler extends CampBaseHandler {
    public CampParticipantHandler(AllCamp allCamp, User user) {
        super(allCamp, user);
    }

    public void joinCamp() {
        if (!(user instanceof Student)) {
            return;
        }
        Student student = (Student) user;
        ArrayList<Camp> availCampArrayList = FilterCamp.getSignUpCamp(allCamp, student);
        Camp selectedCamp = CampSelectionBuilder.selectCamp(availCampArrayList);
        if (selectedCamp == null) {
            return;
        }
        LocalDate currentDateObj = LocalDate.now();
        Date currentDate = Date.from(currentDateObj.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date registrationDeadLine = selectedCamp.getCampInformation().getCampDate().getDates()[2];
        if (currentDate.after(registrationDeadLine)) {
            System.out.println("Sorry, the registration deadline for this camp has passed.");
            return;
        }

        boolean attendee = true;
        if (selectedCamp.getUserManager().getParticipantManager().getRemainingSlots(ROLES.COMMITTEE) > 0
                && student.getCommittee() == null) {
            System.out.print("Do you want to join as a committee member or attendee? C/A\nChoice: ");
            if (MainProgram.sc.nextLine().toLowerCase().equals("c")) {
                attendee = false;
            }
        }
        if (!student.registerCamp(selectedCamp, attendee)) {
            System.out.println("Registration unsuccessful");
        }
    }

    public void withdrawCamp() {
        if (!(user instanceof Student)) {
            return;
        }
        Student student = (Student) user;
        if (student.getAttendee() == null) {
            System.out.println("There's no camp for you to withdraw as a registered attendee.\n");
        } else {
            ArrayList<Camp> campArrayList = student.getAttendee().getCamps();
            if (campArrayList == null) {
                return;
            }
            Camp camp = CampSelectionBuilder.selectCamp(campArrayList);
            if (camp == null)
                return;
            student.withdrawAttendeeCamp(camp);
            System.out.println("Withdraw successful! You are not allowed to join this camp again.");
        }
        return;
    }
}
