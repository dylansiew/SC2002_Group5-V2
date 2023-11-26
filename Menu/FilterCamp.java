package Menu;

import java.util.*;
import Controller.AllCamp;
import Camp.*;
import Users.*;
/**
 * The `FilterCamp` class provides utility methods for filtering and selecting camps based on various criteria.
 * It allows filtering by date and location, as well as selecting camps available for signup and viewing.
 */
public class FilterCamp {
    /**
     * Retrieves camps that a student can sign up for based on visibility, faculty, slots of the camps.
     * @param allCamp The repository of all camps in the system.
     * @param student The student for whom camps are filtered.
     * @return An ArrayList of camps that the student can sign up for.
     */
    public static ArrayList<Camp> getSignUpCamp(AllCamp allCamp, Student student) {
        ArrayList<Camp> campArrayList = new ArrayList<>();
        ArrayList<Camp> allCampArrayList = allCamp.getCamps();

        for (Camp camp : allCampArrayList) {
            ArrayList<User> attendee = camp.getUserManager().getParticipantManager()
                    .getParticipantsArrayList(Roles.ROLES.ATTENDEE);
            ArrayList<User> committee = camp.getUserManager().getParticipantManager()
                    .getParticipantsArrayList(Roles.ROLES.COMMITTEE);
            CampInformation campInformation = camp.getCampInformation();
            if (attendee == null || committee == null || campInformation == null) {
                continue;
            }
            ArrayList<Faculty.FACULTY_TYPE> facultyArrayList = campInformation.geCampFaculty().getFaculty();
            if (camp.getCampVisibility().getVisbility() && !attendee.contains(student) && !committee.contains(student)
                    && (facultyArrayList.contains(student.getFaculty())
                            || facultyArrayList.contains(Faculty.FACULTY_TYPE.UNIVERSE))
                    && camp.getUserManager().getParticipantManager().getRemainingSlots(Roles.ROLES.STUDENT) > 0) {
                campArrayList.add(camp);
            }

        }
        return campArrayList;
    }
    /**
     * Retrieves camps that are available based on visibility and faculty.
     * @param allCamp The repository of all camps in the system.
     * @param student The student for whom camps are filtered.
     * @return An ArrayList of camps that are available based on the student's criteria.
     */
    public static ArrayList<Camp> getAvailableCamps(AllCamp allCamp, Student student) {
        ArrayList<Camp> campArrayList = new ArrayList<>();
        ArrayList<Camp> allCampArrayList = allCamp.getCamps();

        for (Camp camp : allCampArrayList) {
            CampInformation campInformation = camp.getCampInformation();
            if (campInformation == null) {
                continue;
            }
            ArrayList<Faculty.FACULTY_TYPE> facultyArrayList = campInformation.geCampFaculty().getFaculty();

            if (camp.getCampVisibility().getVisbility() && (facultyArrayList.contains(student.getFaculty())
                    || facultyArrayList.contains(Faculty.FACULTY_TYPE.UNIVERSE))) {
                campArrayList.add(camp);
            }

        }
        return campArrayList;
    }
}