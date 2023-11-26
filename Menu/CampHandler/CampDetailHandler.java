package Menu.CampHandler;

import Camp.CampCommittee;
import Camp.CampInformation;
import Controller.AllCamp;
import Program.MainProgram;
import Users.User;

import java.util.*;
/**
 * The `CampDetailHandler` class is responsible for handling operations related to camp details, such as creating or updating
 * camp information. It provides methods for interacting with the user to input and modify camp details.
 */
public class CampDetailHandler extends CampBaseHandler {
    /**
     * Initializes a new instance of the `CampDetailHandler` class with the specified `AllCamp` and `User` objects.
     * @param allCamp The `AllCamp` object containing information about all camps.
     * @param user    The `User` object representing the user interacting with camp-related operations.
     */
    public CampDetailHandler(AllCamp allCamp, User user) {
        super(allCamp, user);
    }
    /**
     * Creates a new camp name by taking user input and ensuring it is unique.
     * @return The new camp name.
     */
    public String createName() {
        String newName;
        while (true) {
            System.out.print("Enter new camp name: ");
            newName = MainProgram.sc.nextLine();
            if (allCamp.hasCampName(newName)) {
                System.out.println("This camp name already exists. Please enter a different name.");
            } else {
                break;
            }
        }
        return newName;
    }
    /**
     * Creates an array of event dates (start and end dates) for the camp.
     * @param campInformation The existing camp information, or null if creating a new camp.
     * @return An array of event dates [start date, end date].
     */
    public Date[] createEventDates(CampInformation campInformation) {
        Date startDate = null;
        Date endDate = null;

        while (true) {
            try {
                System.out.print("Camp new commencement date in yyyy-MM-dd format: ");
                String dateString = MainProgram.sc.nextLine();
                startDate = MainProgram.sdf.parse(dateString);
                if (campInformation != null && startDate.before(campInformation.getCampDate().getDates()[2])) {
                    System.out.println("Start date must be after the registration deadline.");
                    continue;
                }
                if (startDate.before(MainProgram.sdf.parse(java.time.LocalDate.now().toString()))) {
                    System.err.println("The date has already passed.");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.err
                        .println("Please enter a valid date in yyyy-MM-dd format.");
            }
        }

        while (true) {
            try {
                System.out.print("Camp new end date in yyyy-MM-dd format: ");
                String dateString = MainProgram.sc.nextLine();
                endDate = MainProgram.sdf.parse(dateString);

                if (endDate.before(startDate)) {
                    System.err.println("End date must be after the commencement date.");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.err
                        .println("Please enter a valid date in yyyy-MM-dd format.");
            }
        }
        Date[] dateArr = { startDate, endDate };
        return dateArr;
    }
    /**
     * Creates a registration date for the camp by taking user input.
     * @param eventStartDate The start date of the camp event.
     * @return The registration deadline date.
     */
    public Date createRegistrationDate(Date eventStartDate) {
        Date newRegistrationDate = null;
        while (true) {
            try {
                System.out.print("Camp registration date in yyyy-MM-dd format: ");
                String dateString = MainProgram.sc.nextLine();
                newRegistrationDate = MainProgram.sdf.parse(dateString);
                if (eventStartDate.before(newRegistrationDate)) {
                    System.out.println("Registration deadline must be before the commencement date.");
                    continue;
                }
                if (newRegistrationDate.before(MainProgram.sdf.parse(java.time.LocalDate.now().toString()))) {
                    System.err.println("The date has already passed.");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.err
                        .println("Please enter a valid date in yyyy-MM-dd format.");
            }
        }
        return newRegistrationDate;
    }
    /**
     * Creates the total number of slots available for the camp.
     * @return The total number of slots.
     */
    public int createTotalSlots() {
        int newTotalSlots = 0;
        boolean validTotalSlots = false;
        while (!validTotalSlots) {
            System.out.print("Enter new total slots: ");
            newTotalSlots = MainProgram.scanInt();
            if (newTotalSlots < 0) {
                continue;
            }
            validTotalSlots = true;
        }
        return newTotalSlots;
    }
    /**
     * Creates a description for the camp.
     * @return The camp description.
     */
    public String createCampDescription() {
        System.out.print("Enter new description: ");
        String newDescription = MainProgram.sc.nextLine();
        return newDescription;
    }
    /**
     * Creates a boolean value indicating the visibility of the camp.
     * @return True if the camp is visible, false if not.
     */
    public boolean createVisibility() {
        System.out.print("Change visibility (1 for visible, 2 for not visible): ");
        int visibilityChoice = MainProgram.scanInt();
        return visibilityChoice == 1;
    }
    /**
     * Creates a location for the camp.
     * @return The camp location.
     */
    public String createLocation() {
        System.out.print("Enter new location: ");
        String newLocation = MainProgram.sc.nextLine();
        return newLocation;
    }
    /**
     * Creates the size of the camp committee, ensuring it is within valid bounds.
     * @param totalSlots The total number of slots available for the camp.
     * @return The committee size.
     */
    public int createCommitteeSize(int totalSlots) {
        int committee_slots;
        do {
            System.out.printf("Committee slots: (Max %d): ", CampCommittee.MAX_COMMITTEE_SLOTS);
            committee_slots = MainProgram.scanInt();
        } while (committee_slots > CampCommittee.MAX_COMMITTEE_SLOTS || committee_slots < 0
                || committee_slots >= totalSlots);
        return committee_slots;
    }
}
