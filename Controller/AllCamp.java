
package Controller;

import java.io.Serializable;
import java.util.*;
import Camp.*;
import Users.*;

/**
 * `AllCamp` stores the collection of all camps. It can only be instantiate once
 */
public class AllCamp implements Serializable {
    private static AllCamp instance;
    private ArrayList<Camp> allCamp = new ArrayList<>();

     /**
     * Create an array to store all of the camps in `allCamp`.
     */
    private AllCamp() {
        allCamp = new ArrayList<>();
    }

    /**
     * Get the `AllCamp` instance.
     * @return The singleton instance of `AllCamp`.
     */
    public static AllCamp getInstance() {
        if (instance == null) {
            instance = new AllCamp();
        }
        return instance;
    }

    /**
     * Add the camp reference in `allCamp`.
     * Sort the camps in `allCamp` in alphabetical order.
     * @param camp The camp to be added.
     */
    public void addCamp(Camp camp) {
        allCamp.add(camp);
        Collections.sort(allCamp);
    }

    /**
     * Delete the camp reference in `allCamp`.
     * Delete the camp reference in staff's camps.
     * @param camp The camp to be deleted.
     */
    public void deleteCamp(Camp camp) {
        if ((camp.getUserManager().getParticipantManager().getCurrentSize(Roles.ROLES.STUDENT)) == 0) {
            allCamp.remove(camp);
            User user = camp.getUserManager().getCampOrganiserManager().getOrganiser();
            if(user == null || !(user instanceof Staff)){
                return;
            }
            Staff staff = (Staff) user;
            staff.removeCamp(camp);
        }
    }

    /**
     * Get `allCamp`.
     * @return An ArrayList containing all the camps.
     */
    public ArrayList<Camp> getCamps() {
        return this.allCamp;
    }

    /**
     * Filter camps based on visibility.
     * @return An ArrayList containing visible camps.
     */
    public ArrayList<Camp> filterCamp() {
        ArrayList<Camp> campArrayList = new ArrayList<>();
        for (Camp camp : allCamp) {
            if (camp == null) {
                continue;
            }
            if (camp.getCampVisibility().getVisbility()) {
                campArrayList.add(camp);
            }
        }
        Collections.sort(campArrayList);
        return campArrayList;
    }

    /** Print all of the camps */
    public void printAllCamp() {
        for (Camp camp : allCamp) {
            System.out.println(camp.toString()+ "\n");
        }
    }

    /**
     * Check if a camp with the given name already exists.
     * @param name The name to check.
     * @return True if a camp with the same name exists, false otherwise.
     */
    public boolean hasCampName(String name) {
        for (Camp camp : allCamp) {
            CampInformation campInformation = camp.getCampInformation();
            if (campInformation == null) {
                continue;
            }
            if (campInformation.getName().getString().toLowerCase().equals(name.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

}
