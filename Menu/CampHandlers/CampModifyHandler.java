package Menu.CampHandlers;

import Camp.*;
import Controller.*;
import Program.*;
import Users.*;
import Users.Roles.ROLES;

import java.util.*;
/**
 * The `CampModifyHandler` class is responsible for handling camp modification operations, such as creating new camps,
 * editing existing camps, and deleting camps. It provides methods to create, edit, and delete camps, with various
 * options for customization.
 */
public class CampModifyHandler extends CampBaseHandler {
    private CampDetailHandler campDetailHandler;
    /**
     * Initializes a new instance of the `CampModifyHandler` class with the specified `AllCamp` and `User` objects.
     * @param allCamp The `AllCamp` object containing information about all camps.
     * @param user    The `User` object representing the user interacting with camp-related operations.
     */
    public CampModifyHandler(AllCamp allCamp, User user) {
        super(allCamp, user);
        campDetailHandler = new CampDetailHandler(allCamp, user);
    }
    /**
     * Creates a new camp based on user input and adds it to the system.
     * This method is accessible to staff users.
     */
    public void createCamp() {
        if (!(user instanceof Staff)) {
            return;
        }
        Staff staff = (Staff) user;
        System.out.println("Let's create a camp!");

        String name = campDetailHandler.createName();

        String description = campDetailHandler.createCampDescription();

        int totalSlot = campDetailHandler.createTotalSlots();

        String location = campDetailHandler.createLocation();

        ArrayList<Faculty.FACULTY_TYPE> facultyArrayList = new ArrayList<>();
        System.out.println("Choose the faculties that can participate:");
        System.out.println(
                "1.SCSE, 2.EEE, 3.SSS, 4.NBS, 5.CCEB, 6.CEE, 7.MSE, 8.MAE, 9.ADM, 10.SOH, 11.WKWSCI, 12.SBS, 13.SPMS, 14.ASE, 15.LKC, 16.NIE, 17.All Faculties, 18.Exit");
        int facultyChoice;
        int faculty_counter = 1;
        do {
            System.out.printf("Faculty %d: ", faculty_counter);
            faculty_counter += 1;
            facultyChoice = MainProgram.scanInt();
            if (facultyChoice > 18 || facultyChoice < 1) {
                System.out.println("Invalid faculty choice. Try again.");
                faculty_counter -= 1;
                continue;
            }
            if (facultyChoice == 17) {
                facultyArrayList.clear();
                facultyArrayList.add(Faculty.FACULTY_TYPE.UNIVERSE);
                break;
            } else {
                Faculty.FACULTY_TYPE choice = Faculty.getFacultyFromChoice(facultyChoice);
                if (choice == null) {
                    continue;
                }
                facultyArrayList.add(choice);
            }
        } while (facultyChoice != 18);

        boolean visible = campDetailHandler.createVisibility();
        Date[] dateArr = campDetailHandler.createEventDates(null);
        Date registrationDate = campDetailHandler.createRegistrationDate(dateArr[0]);
        int committee_slots = campDetailHandler.createCommitteeSize(totalSlot);
        Camp newCamp = new Camp(name, dateArr[0], dateArr[1], totalSlot, location, registrationDate, staff,
                facultyArrayList, visible, description, committee_slots);
        staff.addCamp(newCamp);
        allCamp.addCamp(newCamp);
    }
    /**
     * Allows staff users to edit the details of an existing camp, such as its name, dates, location, and more.
     * Users can choose which aspects of the camp to edit.
     */
    public void editCamps() {
        if (!(user instanceof Staff)) {
            return;
        }
        Staff staff = (Staff) user;
        System.out.println("Which camp would you like to edit?");
        Camp editCamp = CampSelectionBuilder.selectCamp(staff.getCampsCreated());
        if (editCamp == null) {
            return;
        }
        CampInformation campInformation = editCamp.getCampInformation();

        System.out.println(campInformation.toString());
        while (true) {
            System.out.print(
                    "Choose what aspect you would like to edit:\n(1)\tCamp Name\n(2)\tCamp Dates\n(3)\tTotal Slots\n(4)\tLocation\n(5)\tRegistration Deadline\n(6)\tFaculties\n(7)\tVisibility\n(8)\tDescription\n(9)\tCommittee Slots\n(10)\tExit\nChoice: ");
            int choice = MainProgram.scanInt();

            switch (choice) {
                case 1:
                    campInformation.getName().setString(campDetailHandler.createName());
                    break;
                case 2:
                    ArrayList<User> studentArrayList = editCamp.getUserManager().getParticipantManager()
                            .getParticipantsArrayList(Roles.ROLES.STUDENT);
                    Date[] oldDates = editCamp.getCampInformation().getCampDate().getDates();
                    for (User user : studentArrayList) {
                        if (!(user instanceof Student)) {
                            continue;
                        }
                        Student student = (Student) user;
                        student.getBusyDate().removeDates(oldDates);
                    }
                    Date[] dateArray = campDetailHandler.createEventDates(campInformation);
                    campInformation.getCampDate().setStartDate(dateArray[0]);
                    campInformation.getCampDate().setEndDate(dateArray[1]);
                    for (User user : studentArrayList) {
                        if (!(user instanceof Student)) {
                            continue;
                        }
                        Student student = (Student) user;
                        student.getBusyDate().addDates(dateArray);
                    }
                    break;
                case 3:
                    editCamp.getUserManager().getParticipantManager().getCampParticipant(Roles.ROLES.ATTENDEE)
                            .setTotalSlot(campDetailHandler.createTotalSlots());
                    break;
                case 4:
                    campInformation.getLocation().setString(campDetailHandler.createLocation());
                    break;
                case 5:
                    Date newRegDate = campDetailHandler
                            .createRegistrationDate(campInformation.getCampDate().getDates()[2]);
                    if (newRegDate == null) {
                        break;
                    }
                    campInformation.getCampDate().setRegistrationDeadline(newRegDate);
                    break;

                case 6:
                    System.out.println("\nCurrent faculties:");
                    CampFaculty campFaculty = campInformation.geCampFaculty();
                    int facultyEditChoice = 0;
                    do {
                        int i = 1;
                        for (Faculty.FACULTY_TYPE faculty : campFaculty.getFaculty()) {
                            System.out.printf("%d->\t%s\n", i, faculty);
                            i += 1;
                        }
                        System.out.print(
                                "\nDo you want to: \n(1)\tAdd a faculty\n(2)\tRemove a faculty\n(3)\tExit\nChoice: ");
                        facultyEditChoice = MainProgram.scanInt();

                        switch (facultyEditChoice) {
                            case 1:
                                System.out.println("Choose the faculties that can participate:");
                                System.out.println(
                                        "1.SCSE, 2.EEE, 3.SSS, 4.NBS, 5.CCEB, 6.CEE, 7.MSE, 8.MAE, 9.ADM, 10.SOH, 11.WKWSCI, 12.SBS, 13.SPMS, 14.ASE, 15.LKC, 16.NIE, 17.All Faculties, 18.Exit");
                                System.out.print("Faculty: ");
                                int addFacultyChoice = MainProgram.scanInt();
                                campFaculty.addFaculty(Faculty.getFacultyFromChoice(addFacultyChoice));
                                break;

                            case 2:
                                System.out.println("Choose the faculties to remove:");
                                System.out.println(
                                        "1.SCSE, 2.EEE, 3.SSS, 4.NBS, 5.CCEB, 6.CEE, 7.MSE, 8.MAE, 9.ADM, 10.SOH, 11.WKWSCI, 12.SBS, 13.SPMS, 14.ASE, 15.LKC, 16.NIE, 17.All Faculties, 18.Exit");
                                int removeFacultyChoice = MainProgram.scanInt();
                                campFaculty.removeFaculty(Faculty.getFacultyFromChoice(removeFacultyChoice));
                                break;

                            case 3:
                                return;

                            default:
                                System.err.println("Invalid choice.");
                                break;
                        }
                    } while (facultyEditChoice < 3 && facultyEditChoice > 0);
                    break;

                case 7:
                    if (editCamp.getUserManager().getParticipantManager().getCurrentSize(Roles.ROLES.STUDENT) > 0) {
                        System.out.println("There are already students in the camp! Unable to change visibility\n");
                        break;
                    }
                    editCamp.getCampVisibility().setVisibility(campDetailHandler.createVisibility());
                    break;
                case 8:
                    campInformation.getDescription().setString(campDetailHandler.createCampDescription());
                    break;
                case 9:
                    CampParticipant campCommittee = editCamp.getUserManager().getParticipantManager()
                            .getCampParticipant(Roles.ROLES.COMMITTEE);
                    if (campCommittee == null) {
                        break;
                    }
                    campCommittee.setTotalSlot(campCommittee.getTotalSlot());
                    break;
                case 10:
                    return;
                default:
                    System.err.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }

    }
    /**
     * Deletes an existing camp. Staff users can choose to delete a camp, but only if there are no students registered
     * in the camp.
     */
    public void deleteCamp() {
        if (!(user instanceof Staff)) {
            return;
        }
        Staff staff = (Staff) user;
        Camp deleteCamp = CampSelectionBuilder.selectCamp(staff.getCampsCreated());
        if (deleteCamp == null) {
            return;
        }
        CampInformation campInformation = deleteCamp.getCampInformation();
        String campName;
        if (campInformation == null) {
            campName = "";
        } else {
            campName = " " + campInformation.getName();
        }

        if (deleteCamp.getUserManager().getParticipantManager().getCurrentSize(ROLES.STUDENT) > 0) {
            System.out.println("There are already students in the camp! Unable to delete camp\n");
            return;
        }

        System.out.printf("Are you sure you want to delete%s camp? Y/N\tChoice: ", campName);
        String choice = MainProgram.sc.nextLine();
        if (choice.toLowerCase().equals("y")) {
            allCamp.deleteCamp(deleteCamp);
        } else {
            System.out.println("Camp deletion failed. Returning to main menu...");
        }
    }
}
