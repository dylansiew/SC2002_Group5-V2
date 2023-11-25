package Users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class BusyDate implements Serializable {
    private ArrayList<Date> busyDates;

    public BusyDate() {
        busyDates = new ArrayList<>();
    }

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
