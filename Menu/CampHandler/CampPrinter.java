package Menu.CampHandler;

import java.util.ArrayList;

import Camp.Camp;
import Camp.CampInformation;

public class CampPrinter {
    public static int printCamps(ArrayList<Camp> campArrayList, int i) {
        for (Camp camp : campArrayList) {
            CampInformation campInformation = camp.getCampInformation();
            if (campInformation == null) {
                continue;
            }
            System.out.printf("%d ->\t%s\n", i, campInformation.getName());
            i += 1;
        }
        return i;
    }
}
