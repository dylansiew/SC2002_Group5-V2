package Controller;

import java.io.Serializable;
import java.util.*;
import Camp.*;
import Users.*;

public class AllCamp implements Serializable {
    private static AllCamp instance;
    private ArrayList<Camp> allCamp = new ArrayList<>();

    private AllCamp() {
        allCamp = new ArrayList<>();
    }

    public static AllCamp getInstance() {
        if (instance == null) {
            instance = new AllCamp();
        }
        return instance;
    }

    public void addCamp(Camp camp) {
        allCamp.add(camp);
        Collections.sort(allCamp);
    }

    public void deleteCamp(Camp camp) {
        if ((camp.getUserManager().getParticipantManager().getCurrentSize(Roles.ROLES.STUDENT)) == 0) {
            allCamp.remove(camp);
            User user = camp.getUserManager().getCampOrganiserManager().getOrganiser();
            if (user == null || !(user instanceof Staff)) {
                return;
            }
            Staff staff = (Staff) user;
            staff.removeCamp(camp);
        }
    }

    public ArrayList<Camp> getCamps() {
        return this.allCamp;
    }

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

    public void printAllCamp() {
        for (Camp camp : allCamp) {
            System.out.println(camp.toString() + "\n");
        }
    }

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
