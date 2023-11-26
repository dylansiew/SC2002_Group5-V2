package Camp;

import java.io.Serializable;
import java.util.*;

import Users.*;
/**
 * The `Camp` class represents a camp event with information about the camp, its participants, and comments related to the camp.
 * It implements the Serializable and Comparable interfaces.
 */
public class Camp implements Serializable, Comparable<Camp> {

    private CampInformation campInformation;
    private CampUserManager campUserManager;
    private CampCommentManager campCommentManager = new CampCommentManager();
    private CampVisibility visibility;

    /**
     * Constructs a new `Camp` instance with the specified details.
     *
     * @param name               The name of the camp.
     * @param startDate          The start date of the camp.
     * @param endDate            The end date of the camp.
     * @param totalSlot          The total number of available slots in the camp.
     * @param location           The location of the camp.
     * @param registrationDeadline The registration deadline for the camp.
     * @param staff              The staff member responsible for the camp.
     * @param faculty            The list of faculties associated with the camp.
     * @param visible            Indicates whether the visibility of the camp for students.
     * @param description        A description of the camp.
     * @param committee_slots    The number of committee slots available in the camp.
     */
    public Camp(String name, Date startDate, Date endDate, int totalSlot, String location, Date registrationDeadline,
            Staff staff, ArrayList<Faculty.FACULTY_TYPE> faculty, Boolean visible, String description,
            int committee_slots) {
        this.campInformation = new CampInformation(name, startDate, endDate, location, registrationDeadline,
            faculty, description);
        this.campUserManager = new CampUserManager(staff, committee_slots, totalSlot);
        this.visibility = new CampVisibility(visible);
    }
    /**
     * Gets the visibility status of the camp.
     * @return The visibility status of the camp.
     */
    public CampVisibility getCampVisibility() {
        return this.visibility;
    }
    /**
     * Gets the user manager for the camp, which manages camp participants.
     * @return The user manager for the camp.
     */
    public CampUserManager getUserManager() {
        return this.campUserManager;
    }
/**
     * Gets the comment manager for the camp, which manages comments related to the camp.
     * @return The comment manager for the camp.
     */
    public CampCommentManager getCampCommentManager() {
        return this.campCommentManager;
    }
    /**
     * Gets the information about the camp.
     *
     * @return The information about the camp.
     */
    public CampInformation getCampInformation() {
        return this.campInformation;
    }
    /**
     * Provide a human-readable format to describe the `Camp` object
     */
    @Override
    public String toString() {
        return campInformation.toString() + campUserManager.toString();
    }
    /**
     * Compares this camp with another camp for sorting camp name in alphabetical order.
     * @param otherCamp The camp to compare to.
     * @return A negative integer, zero, or a positive integer as this camp is less than, equal to, or greater than the other camp.
     */
    @Override
    public int compareTo(Camp otherCamp) {
        return this.campInformation.getName().getString().compareTo(otherCamp.campInformation.getName().getString());
    }
    /**
     * Checks if this camp is equal to another object.
     * @param obj The object to compare to.
     * @return true if the camps are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Camp otherCamp = (Camp) obj;
        return this.campInformation.getName().getString().toLowerCase().equals(otherCamp.getCampInformation().getName().getString().toLowerCase());
    }

}
