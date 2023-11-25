package Program;

import java.text.SimpleDateFormat;
import java.util.*;
import Controller.*;
import Menu.*;
import Users.*;

/**
 * The `MainProgram` class serves as the entry point for the camp management system.
 * It initializes the necessary components and enters the main program loop to handle
 * user interactions and camp management tasks.
 * <p>
 * This class includes utilities for handling serialization of the main object, managing
 * current date settings, and providing a command-line interface for user interaction.
 * </p>
 */

public class MainProgram {
    public static final String mainObj_filename = "mainObj.ser";
    public static final Scanner sc = new Scanner(System.in);
    public static Date currentDate = new Date();
    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    
    /**
     * The main method initializes the program, loads the main object from serialization,
     * and runs the main loop of the application. It provides a console-based interface
     * for user interaction and handles the overall workflow of the camp management system.
     * <p>
     * The method sets up the necessary objects for managing users and camps, prints initial
     * information to the console, and saves the state of the main object upon program completion.
     * </p>
     * 
     * @param args The command-line arguments passed to the program, not used in this application.
     */	
        
    public static void main(String[] args) {
        sdf.setLenient(false);
        MainObj mainObj = SerializationUtil.loadMainObj(mainObj_filename);
        if (mainObj == null) {
            return;
        }

        AllUser allUser = mainObj.getAllUser();
        AllCamp allCamp = mainObj.getAllCamp();

        allUser.printAllUser();
        allCamp.printAllCamp();

        SerializationUtil.saveObj(mainObj, mainObj_filename);

        boolean runProgram = true;
        while (runProgram) {
            asciiart.title();
            System.out.println("Welcome to the Camp Application and Management System");
            System.out.print("\n(1)\tLogin\n(2)\tQuit\nChoice: ");
            int choice = MainProgram.scanInt();

            switch (choice) {
                case 1:
                    User mainUser = AuthenticationMenu.loginMenu(allUser);
                    if (mainUser == null) {
                        continue;
                    }
                    SerializationUtil.saveObj(mainObj, mainObj_filename);

                    UserMenu authenticationMenu = new AuthenticationMenu(mainUser, allUser, allCamp);
                    boolean loop = true;
                    String menuText = "\nMain menu\n(1)\tEdit profile\n(2)\tCamp menu\n(3)\tLogout\nChoice: ";
                    String adminMenuText = "\nMain menu\n(1)\tEdit profile\n(2)\tAdd Users\n(3)\tLogout\nChoice: ";
                    if (mainUser instanceof AdminUser) {
                        menuText = adminMenuText;
                    }
                    while (loop) {
                        System.out.print(menuText);
                        int choice2 = MainProgram.scanInt();
                        switch (choice2) {
                            case 1:
                                authenticationMenu.mainMenu();
                                SerializationUtil.saveObj(mainObj, mainObj_filename);
                                break;
                            case 2:
                                userMenu(mainUser, allUser, allCamp);
                                SerializationUtil.saveObj(mainObj, mainObj_filename);
                                break;
                            default:
                                System.out.print("\033\143");
                                loop = false;
                                break;
                        }
                    }
                    SerializationUtil.saveObj(mainObj, mainObj_filename);

                    break;
                case 2:
                    runProgram = false;
                    asciiart.endcredits();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
        sc.close();

    }

    /**
     * Displays the user menu based on the type of the main user (Staff, Student, or AdminUser).
     * Creates the appropriate user menu interface and invokes its main menu method.
     *
     * @param mainUser The main user for whom the menu is displayed.
     * @param allUser  The instance of AllUser containing user data.
     * @param allCamp  The instance of AllCamp containing camp data.
     */
    private static void userMenu(User mainUser, AllUser allUser, AllCamp allCamp) {
        UserMenu userMenuInterface;
        if (mainUser instanceof Staff) {
            userMenuInterface = new StaffMenu((Staff) mainUser, allUser, allCamp);
        } else if (mainUser instanceof Student) {
            userMenuInterface = new StudentMenu((Student) mainUser, allUser, allCamp);
        } else {
            userMenuInterface = new AdminMenu((AdminUser) mainUser, allUser, allCamp);
        }
        userMenuInterface.mainMenu();
        return;
    }

    public static int scanInt() {
        int val;
        while (true) {
            try {
                val = sc.nextInt();
                sc.nextLine();
                return val;
            } catch (Exception e) {
                System.out.print("Invalid input. Try again!\nChoice: ");
                sc.nextLine();
                continue;
            }
        }
    }
}
