package Menu.CampHandlers;

import java.util.ArrayList;

import Camp.Camp;
import Camp.CampInformation;
import Controller.AllCamp;
import Menu.FilterCamp;
import Users.Student;
import Users.User;
/**
 * The `CampViewHandler` class is responsible for handling operations related to viewing camps, including camps that
 * the user has joined, available camps, and camp members. It provides methods to view information about camps and their
 * members.
 */
public class CampViewHandler extends CampBaseHandler {
    /**
     * Initializes a new instance of the `CampViewHandler` class with the specified `AllCamp` and `User` objects.
     * @param allCamp The `AllCamp` object containing information about all camps.
     * @param user    The `User` object representing the user interacting with camp viewing operations.
     */
    public CampViewHandler(AllCamp allCamp, User user) {
        super(allCamp, user);
    }
    /**
     * Allows a student to view the camps they have joined as an attendee or committee member and displays relevant information.
     */
    public void viewJoinedCamps() {
        if (!(user instanceof Student)) {
            return;
        }
        Student student = (Student) user;
        if (student.getCommittee() != null) {
            Camp targetCamp = student.getCommittee().getCamp();
            if (targetCamp == null) {
                return;
            }
            CampInformation targetCampInformation = targetCamp.getCampInformation();
            if (targetCampInformation == null) {
                return;
            }
            System.out.printf("Committee member of: %s\n", targetCampInformation.getName());
        }
        if (student.getAttendee() != null) {
            ArrayList<Camp> campArrayList = student.getAttendee().getCamps();
            if (campArrayList.size() == 0) {
                System.out.print("\nYou haven't signed up for any camp as attendee...");
                return;
            }
            int i = 1;
            System.out.print("Attendee of:\n");
            i = CampPrinter.printCamps(campArrayList, i);
        } else {
            System.out.print("\nYou haven't signed up for any camp as attendee...");
        }
    }
    /**
     * Allows a student to view available camps that they can sign up for and optionally view details of a selected camp.
     */
    public void viewAvailableCamps() {
        if (!(user instanceof Student)) {
            return;
        }
        Student student = (Student) user;
        ArrayList<Camp> availCampArrayList = FilterCamp.getAvailableCamps(allCamp, student);
        if (availCampArrayList.isEmpty()) {
            System.out.println("No camp available to view");
            return;
        }

        Camp selectedCamp = CampSelectionBuilder.selectCamp(availCampArrayList);
        if (selectedCamp == null)
            return;
        // System.out.println(selectedCamp.toString());
        return;
    }
    /**
     * Allows the user to view the members of a selected camp, including attendees and committee members.
     */
    public void viewCampMembers() {
        Camp targetCamp = CampSelectionBuilder.selectCamp(allCamp.getCamps());
        if (targetCamp == null) {
            return;
        }
        System.out.println(targetCamp.getUserManager().toString());
    }
}
