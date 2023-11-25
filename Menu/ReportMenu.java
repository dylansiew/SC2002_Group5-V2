package Menu;

import Camp.*;
import Controller.*;
import Menu.CampHandler.CampSelectionBuilder;
import Program.MainProgram;
import Users.*;

public class ReportMenu extends UserMenu {

    public ReportMenu(User user, AllUser allUser, AllCamp allCamp) {
        super(user, allUser, allCamp);
    }

    public void mainMenu() {
        Camp selectedCamp = null;

        try {
            if (user instanceof Staff) {
                Staff staff = (Staff) user;
                selectedCamp = CampSelectionBuilder.selectCamp(staff.getCampsCreated());
            } else if (user instanceof Student) { // using the committee role
                Student student = (Student) user;
                selectedCamp = student.getCommittee().getCamp();
            }
        } catch (NullPointerException e) {
            System.out.println("No camp can be selected for report generation. Returning to the previous menu...");
            return;
        }

        if (selectedCamp == null) {
            System.out.println("No camp can be selected for report generation. Returning to the previous menu...");
            return;
        }

        String menuText = "\nWhat type of report do you want to generate?\n(1)\tAttendee list report\n(2)\tCommittee list report\n"
                +
                "(3)\tBoth attendee and committee lists report\n(4)\tStudent enquiry report\n";
        if (user instanceof Staff)
            menuText += "(5)\tCommittee Performance Report\n(6)\tQuit\nChoice: ";
        else if (user instanceof Student)
            menuText += "(5)\tQuit\nChoice: ";
        while (true) {
            System.out.printf(menuText);

            int reportTypeChoice = MainProgram.scanInt();

            switch (reportTypeChoice) {
                case 1:
                    Report.generateListReport(selectedCamp, Roles.ROLES.ATTENDEE);
                    break;
                case 2:
                    Report.generateListReport(selectedCamp, Roles.ROLES.COMMITTEE);
                    break;
                case 3:
                    Report.generateListReport(selectedCamp, Roles.ROLES.STUDENT);
                    break;
                case 4:
                    Report.generateStudentEnquiryReport(selectedCamp);
                    break;
                case 5:
                    if (user instanceof Staff) {
                        Report.generatePerformanceReport(selectedCamp);
                        break;
                    }
                default:
                    System.out.println("Quitting report generation. Returning to the previous menu...");
                    return;
            }
        }
    }

}