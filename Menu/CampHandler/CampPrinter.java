package Menu.CampHandler;

import java.util.ArrayList;

import Camp.Camp;
import Camp.CampInformation;

/**
 * The `CampPrinter` class provides a utility method for printing a list of camps.
 */
public class CampPrinter {
    /**
     * Prints a list of camps with numbering.
     * @param campArrayList The list of camps to print.
     * @param i             The choice numbering to be chosen in the menu page.
     * @return The next available index after printing. Used by CampMenu to choose the number for exit.
     */
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
