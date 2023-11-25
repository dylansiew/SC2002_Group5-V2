package Controller;

import java.util.Date;

import Program.MainProgram;
/**
 * `SystemSimulatedDateManager` is responsible for managing a simulated date within the system.
 * This date can be used for testing and simulation purposes. It allows setting and retrieving a simulated date.
 * The default simulated date is initialized to the actual date in the real
*/
public class SystemSimulatedDateManager {
    private static Date date = new Date(); // default: actual date in real world
    
    /**
     * Get the current simulated date.
     */
    public static Date getSystemSimulatedDate() {
        return date;
    }
    /**
     * Set the system's simulated date using a string representation in the "yyyy-MM-dd" format.
     * If the provided date string is invalid, the method will prompt the user to enter a valid date.
     * @param dateString The string representation of the date in "yyyy-MM-dd" format.
     */
    public static void setSystemSimulatedDate(String dateString) {
        while (true) {
            try {
                date = MainProgram.sdf.parse(dateString);
                break;
            } catch (Exception e) {
                System.err.println("Please enter a valid date in yyyy-MM-dd format.");
            }
        }
    }
}
