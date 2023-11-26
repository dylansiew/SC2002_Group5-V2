package Camp;
import java.io.Serializable;
import java.util.*;

/**
 * The `CampDate` class represents important dates associated with a camp, including the start date, end date, and registration deadline.
 * It provides methods to get and set these dates.
 */
public class CampDate  implements Serializable{
    private Date startDate;
    private Date endDate;
    private Date registrationDeadline;
    /**
     * Constructs a new `CampDate` instance with the specified start date, end date, and registration deadline.
     * @param starDate           The start date of the camp.
     * @param endDate            The end date of the camp.
     * @param registrationDeadline The registration deadline for the camp.
     */
    public CampDate(Date starDate, Date endDate, Date registrationDeadline){
        this.startDate = starDate;
        this.endDate = endDate;
        this.registrationDeadline = registrationDeadline;
    }
    /**
     * Gets an array of dates containing the start date, end date, and registration deadline.
     * @return An array of dates representing camp-related dates.
     */
    public Date[] getDates(){
        Date[] campDate = {startDate,endDate, registrationDeadline};
        return campDate;
    }
    /**
     * Sets the start date of the camp.
     * @param newStartDate The new start date to be set.
     */
    public void setStartDate(Date newStartDate){
        if(newStartDate == null){
            return;
        }
        else if(newStartDate.after(endDate) || newStartDate.before(registrationDeadline)){
            System.out.println("Error updating start date");
            return;
        }
        startDate = newStartDate;
    }
    /**
     * Sets the end date of the camp.
     * @param newEndDate The new end date to be set.
     */
    public void setEndDate(Date newEndDate){
        if(newEndDate == null){
            return;
        }
        else if(newEndDate.before(startDate) || newEndDate.before(registrationDeadline)){
            System.out.println("Error updating end date");
            return;
        }
        endDate = newEndDate;
    }
    /**
     * Sets the registration deadline for the camp.
     * @param newRegDate The new registration deadline to be set.
     */
    public void setRegistrationDeadline(Date newRegDate){
        if(newRegDate == null){
            return;
        }
        else if(newRegDate.after(startDate) || newRegDate.after(registrationDeadline)){
            System.out.println("Error updating registration date");
            return;
        }
        registrationDeadline = newRegDate;
    }
    /**
     * Returns a string representation of the camp dates, including the start date, end date, and registration deadline.
     * @return A string containing the camp dates.
     */
    public String toString(){
        return "Start Date: " + startDate.toString() + "\nEnd Date: " + endDate.toString() + "\nRegistration deadline: " + registrationDeadline.toString() + "\n";
    }
}
