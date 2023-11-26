package Camp;

import java.io.Serializable;
import java.util.*;

import Camp.CampString.TYPE;
import Users.*;
/**
 * The `CampInformation` class represents the information associated with a camp, including its name, location, faculties,
 * dates, and description.
 */
public class CampInformation implements Serializable {
    private CampString name;
    private CampString location;
    private CampFaculty campFaculty;
    private CampDate campDate;
    private CampString description;
    /**
     * Constructs a new `CampInformation` instance with the specified parameters.
     * @param name                The name of the camp.
     * @param startDate           The start date of the camp.
     * @param endDate             The end date of the camp.
     * @param location            The location of the camp.
     * @param registrationDeadline The registration deadline for the camp.
     * @param faculty             The list of faculty types associated with the camp.
     * @param description         The description of the camp.
     */
    public CampInformation(String name, Date startDate, Date endDate, String location,
            Date registrationDeadline, ArrayList<Faculty.FACULTY_TYPE> faculty,
            String description) {
        this.name = new CampString(name, TYPE.NAME);
        this.location = new CampString(location, TYPE.LOCATION);
        this.description = new CampString(description, TYPE.DESCRIPTION);
        this.campFaculty = new CampFaculty(faculty);
        this.campDate = new CampDate(startDate, endDate, registrationDeadline);
    }
    /**
     * Retrieves the faculties associated with the camp.
     * @return The camp's faculties.
     */
    public CampFaculty geCampFaculty() {
        return this.campFaculty;
    }
    /**
     * Retrieves the camp's date information, including start date, end date, and registration deadline.
     * @return The camp's date information.
     */
    public CampDate getCampDate() {
        return this.campDate;
    }
    /**
     * Retrieves the name of the camp.
     *
     * @return The camp's name.
     */
    public CampString getName() {
        return name;
    }
    /**
     * Retrieves the location of the camp.
     * @return The camp's location.
     */
    public CampString getLocation() {
        return location;
    }
    /**
     * Retrieves the description of the camp.
     * @return The camp's description.
     */
    public CampString getDescription() {
        return description;
    }
    /**
     * Returns a string representation of the camp's information.
     *
     * @return A string containing the camp's details.
     */
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
