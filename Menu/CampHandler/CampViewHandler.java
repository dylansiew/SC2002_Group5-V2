package Menu.CampHandler;

import java.util.ArrayList;

import Camp.Camp;
import Camp.CampInformation;
import Controller.AllCamp;
import Menu.FilterCamp;
import Users.Student;
import Users.User;

public class CampViewHandler extends CampBaseHandler {
    public CampViewHandler(AllCamp allCamp, User user) {
        super(allCamp, user);
    }

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

    public void viewCampMembers() {
        Camp targetCamp = CampSelectionBuilder.selectCamp(allCamp.getCamps());
        if (targetCamp == null) {
            return;
        }
        System.out.println(targetCamp.getUserManager().toString());
    }
}
