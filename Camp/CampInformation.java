package Camp;

import java.io.Serializable;
import java.util.*;

import Camp.CampString.TYPE;
import Users.*;

public class CampInformation implements Serializable {
    private CampString name;
    private CampString location;
    private CampFaculty campFaculty;
    private CampDate campDate;
    private CampString description;

    public CampInformation(String name, Date startDate, Date endDate, String location,
            Date registrationDeadline, ArrayList<Faculty.FACULTY_TYPE> faculty,
            String description) {
        this.name = new CampString(name, TYPE.NAME);
        this.location = new CampString(location, TYPE.LOCATION);
        this.description = new CampString(description, TYPE.DESCRIPTION);
        this.campFaculty = new CampFaculty(faculty);
        this.campDate = new CampDate(startDate, endDate, registrationDeadline);
    }

    public CampFaculty geCampFaculty() {
        return this.campFaculty;
    }

    public CampDate getCampDate() {
        return this.campDate;
    }

    public CampString getName() {
        return name;
    }

    public CampString getLocation() {
        return location;
    }

    public CampString getDescription() {
        return description;
    }

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder();
        info.append(name.toString()).append("\n");
        info.append(campDate.toString());
        info.append(location.toString()).append("\n");
        info.append(campFaculty.toString());
        info.append(description.toString()).append("\n");
        return info.toString();
    }
}
