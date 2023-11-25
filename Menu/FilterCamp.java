package Menu;

import java.util.*;
import Controller.AllCamp;
import Camp.*;
import Users.*;

public class FilterCamp {
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