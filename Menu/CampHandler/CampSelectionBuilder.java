package Menu.CampHandler;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import Camp.*;
import Program.*;
/**
 * The `CampSelectionBuilder` class provides utility methods for selecting and filtering camps.
 */
public class CampSelectionBuilder {
    /**
     * Selects a camp from the provided list of camps, optionally applying filters.
     * @param campArrayList The list of camps to choose from.
     * @return The selected camp or null if no camp is chosen.
     */
    public static Camp selectCamp(ArrayList<Camp> campArrayList) {
        System.out.println("Would you like to filter camps?\nChoice(Y/N)");
        if (MainProgram.sc.nextLine().toLowerCase().equals("y")) {
            campArrayList = CampSelectionBuilder.applyFilters(campArrayList);
        }
        if (campArrayList == null) {
            return null;
        }
        if (campArrayList.isEmpty()) {
            System.out.println("No camps to choose to! Returning to main menu...");
            return null;
        }

        System.out.println("\nSelect camp:");
        while (true) {
            int i = 1;
            i = CampPrinter.printCamps(campArrayList, i);
            System.out.printf("%d ->\tExit\n", i);
            System.out.print("\nChoice: ");
            int choice = MainProgram.scanInt();
            if (choice == i) {
                return null;
            }
            while (choice > campArrayList.size() || choice <= 0) {
                System.out.print("Invalid input\nChoice: ");
                choice = MainProgram.scanInt();
            }
            Camp targetCamp = campArrayList.get(choice - 1);
            System.out.printf("\n%s selected\n", targetCamp.getCampInformation().getName());
            System.out.println(targetCamp.toString());
            return targetCamp;
        }
    }
    /**
     * Creates a date filter predicate based on a start and end date.
     * @param startDate The start date for the filter.
     * @param endDate   The end date for the filter.
     * @return A predicate that filters camps based on their event dates.
     */
    public static Predicate<Camp> addDateFilter(Date startDate, Date endDate) {
        return new Predicate<Camp>() {
            public boolean test(Camp camp) {
                Date[] campDates = camp.getCampInformation().getCampDate().getDates();
                return !campDates[0].before(startDate) && !campDates[1].after(endDate);
            }
        };
    }
    /**
     * Creates a location filter predicate based on a list of locations.
     *
     * @param locations The list of locations to filter by.
     * @return A predicate that filters camps based on their locations.
     */
    public static Predicate<Camp> addLocationFilter(ArrayList<String> locations) {
        return new Predicate<Camp>() {
            public boolean test(Camp camp) {
                String campLocation = camp.getCampInformation().getLocation().getString();
                return locations.contains(campLocation);
            }
        };
    }
    /**
     * Applies filters to a list of camps based on user input.
     * @param camps The list of camps to filter.
     * @return The filtered list of camps.
     */
    public static ArrayList<Camp> applyFilters(ArrayList<Camp> camps) {
        ArrayList<Predicate<Camp>> filters = new ArrayList<>();

        System.out.println("Would you like to filter camps by events dates?\nChoice(Y/N)");
        if (MainProgram.sc.nextLine().toLowerCase().equals("y")) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            Date filterStartDate = null;
            Date filterEndDate = null;
            while (true) {
                try {
                    System.out.print(
                            "Enter the start date for the camp filter in yyyy-MM-dd format. This date will be used to find camps starting on or after this date.\nStart Date:");
                    String dateString = MainProgram.sc.nextLine();
                    filterStartDate = sdf.parse(dateString);
                    break;
                } catch (Exception e) {
                    System.err.println("Please enter a valid date in yyyy-MM-dd format.");
                }
            }

            while (true) {
                try {
                    System.out.print(
                            "Enter the end date for the camp filter in yyyy-MM-dd format. This date will be used to find camps ending on or before this date.\nEnd Date:");
                    String dateString = MainProgram.sc.nextLine();
                    filterEndDate = sdf.parse(dateString);

                    if (filterEndDate.before(filterStartDate)) {
                        System.err.println("End date must be after the start date.");
                        continue;
                    }
                    break;
                } catch (Exception e) {
                    System.err.println("Please enter a valid date in yyyy-MM-dd format.");
                }
            }
            filters.add(addDateFilter(filterStartDate, filterEndDate));
        }

        System.out.println("Would you like to filter camps by location?\nChoice(Y/N)");
        if (MainProgram.sc.nextLine().toLowerCase().equals("y")) {
            ArrayList<String> locations = new ArrayList<>();
            System.out.print("Enter the locations for the camp filter, one per line. End the input by typing 'end'.\n");
            String location = MainProgram.sc.nextLine();
            do {
                locations.add(location);
                location = MainProgram.sc.nextLine();
            } while (!location.toLowerCase().equals("end"));
            filters.add(addLocationFilter(locations));
        }

        return camps.stream()
                .filter(filters.stream().reduce(x -> true, Predicate::and))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
