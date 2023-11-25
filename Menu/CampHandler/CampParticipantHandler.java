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
/**
 * The `CampParticipantHandler` class is responsible for handling camp participation-related operations for students,
 * including joining camps and withdrawing from camps. It provides methods to allow students to register for camps and
 * withdraw from their registered camps.
 */
public class CampParticipantHandler extends CampBaseHandler {
    /**
     * Initializes a new instance of the `CampParticipantHandler` class with the specified `AllCamp` and `User` objects.
     * @param allCamp The `AllCamp` object containing information about all camps.
     * @param user    The `User` object representing the user interacting with camp participation operations.
     */
    public CampParticipantHandler(AllCamp allCamp, User user) {
        super(allCamp, user);
    }
    /**
     * Allows a student to join a camp based on availability and registration deadlines. Students can choose to join
     * as attendees or committee members if slots are available.
     */
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
    /**
     * Allows a student to withdraw from a camp they have previously registered for as an attendee. Once withdrawn,
     * they cannot join the same camp again.
     */
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
