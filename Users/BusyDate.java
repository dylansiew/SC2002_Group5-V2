package Users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
/**
 * The `BusyDate` class represents a list of dates that are marked as busy.
 * It provides methods for checking date overlaps, adding busy dates, and removing busy dates.
 */
public class BusyDate implements Serializable {
    private ArrayList<Date> busyDates;
    /**
     * Constructs a new `BusyDate` instance with an empty list of busy dates.
     */
    public BusyDate() {
        busyDates = new ArrayList<>();
    }
    /**
     * Checks if there is an overlap between the given new event dates and the existing busy dates.
     *
     * @param newEventDates The start and end dates of the new event.
     * @return True if there is an overlap, false otherwise.
     */
    public boolean isOverlap(Date[] newEventDates) {
        if (newEventDates.length < 2) {
            return true;
        }

        Date newEventStartDate = newEventDates[0];
        Date newEventEndDate = newEventDates[1];

        for (Date busyDate : busyDates) {
            if (!newEventEndDate.before(busyDate)) {
                if (!newEventStartDate.after(busyDate)) {
                    return true;
                }
            }
        }

        return false;
    }
    /**
     * Adds the specified new event dates to the list of busy dates, if there is no overlap.
     * @param newEventDates The start and end dates of the new event to add.
     * @return True if the dates were added successfully, false if there is an overlap.
     */
    public boolean addDates(Date[] newEventDates) {
        if (newEventDates.length < 2) {
            return false;
        }

        Date newEventStartDate = newEventDates[0];
        Date newEventEndDate = newEventDates[1];

        if (isOverlap(newEventDates)) {
            return false;
        }

        Date currentDate = newEventStartDate;
        while (currentDate.before(newEventEndDate) || currentDate.equals(newEventEndDate)) {
            busyDates.add(currentDate);
            currentDate = new Date(currentDate.getTime() + 24 * 60 * 60 * 1000);
        }
        return true;
    }
    /**
     * Removes dates within the specified range from the list of busy dates.
     *
     * @param eventDatesToRemove The start and end dates of the range to remove.
     */
    public void removeDates(Date[] eventDatesToRemove) {
        if (eventDatesToRemove.length < 2) {
            return;
        }

        Date startDate = eventDatesToRemove[0];
        Date endDate = eventDatesToRemove[1];

        if (startDate.after(endDate)) {
            return;
        }

        busyDates.removeIf(date -> !date.before(startDate) && !date.after(endDate));
    }

}
