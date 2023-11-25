package Camp;

import java.io.Serializable;
import java.util.*;

import Users.*;

public class Camp implements Serializable, Comparable<Camp> {

    private CampInformation campInformation;
    private CampUserManager campUserManager;
    private CampCommentManager campCommentManager = new CampCommentManager();
    private CampVisibility visibility;

    public Camp(String name, Date startDate, Date endDate, int totalSlot, String location, Date registrationDeadline,
            Staff staff, ArrayList<Faculty.FACULTY_TYPE> faculty, Boolean visible, String description,
            int committee_slots) {
        this.campInformation = new CampInformation(name, startDate, endDate, location, registrationDeadline,
                faculty, description);
        this.campUserManager = new CampUserManager(staff, committee_slots, totalSlot);
        this.visibility = new CampVisibility(visible);
    }

    public CampVisibility getCampVisibility() {
        return this.visibility;
    }

    public CampUserManager getUserManager() {
        return this.campUserManager;
    }

    public CampCommentManager getCampCommentManager() {
        return this.campCommentManager;
    }

    public CampInformation getCampInformation() {
        return this.campInformation;
    }

    @Override
    public String toString() {
        return campInformation.toString() + campUserManager.toString();
    }

    @Override
    public int compareTo(Camp otherCamp) {
        return this.campInformation.getName().getString().compareTo(otherCamp.campInformation.getName().getString());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Camp otherCamp = (Camp) obj;
        return this.campInformation.getName().getString().toLowerCase()
                .equals(otherCamp.getCampInformation().getName().getString().toLowerCase());
    }

}
