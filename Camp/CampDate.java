package Camp;

import java.io.Serializable;
import java.util.*;

public class CampDate implements Serializable {
    private Date startDate;
    private Date endDate;
    private Date registrationDeadline;

    public CampDate(Date starDate, Date endDate, Date registrationDeadline) {
        this.startDate = starDate;
        this.endDate = endDate;
        this.registrationDeadline = registrationDeadline;
    }

    public Date[] getDates() {
        Date[] campDate = { startDate, endDate, registrationDeadline };
        return campDate;
    }

    public void setStartDate(Date newStartDate) {
        if (newStartDate == null) {
            return;
        } else if (newStartDate.after(endDate) || newStartDate.before(registrationDeadline)) {
            System.out.println("Error updating start date");
            return;
        }
        startDate = newStartDate;
    }

    public void setEndDate(Date newEndDate) {
        if (newEndDate == null) {
            return;
        } else if (newEndDate.before(startDate) || newEndDate.before(registrationDeadline)) {
            System.out.println("Error updating end date");
            return;
        }
        endDate = newEndDate;
    }

    public void setRegistrationDeadline(Date newRegDate) {
        if (newRegDate == null) {
            return;
        } else if (newRegDate.after(startDate) || newRegDate.after(registrationDeadline)) {
            System.out.println("Error updating registration date");
            return;
        }
        registrationDeadline = newRegDate;
    }

    public String toString() {
        return "Start Date: " + startDate.toString() + "\nEnd Date: " + endDate.toString() + "\nRegistration deadline: "
                + registrationDeadline.toString() + "\n";
    }
}
