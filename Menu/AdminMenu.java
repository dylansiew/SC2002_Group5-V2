package Menu;

import java.util.*;
import Controller.*;
import Program.*;
import Users.*;

import java.io.File;
/**
 * The `AdminMenu` class represents the menu and functionality available to an administrator user.
 * It is a subclass of the `UserMenu` class.
 *
 * This class allows administrators to select and add user profiles from CSV files to the application.
 */
public class AdminMenu extends UserMenu {
    /**
     * Constructs an `AdminMenu` object with the provided administrator user, all users, and all camps.
     *
     * @param adminUser The administrator user accessing the menu.
     * @param allUser The collection of all users in the application.
     * @param allCamp The collection of all camps in the application.
     */
    public AdminMenu(AdminUser adminUser, AllUser allUser, AllCamp allCamp) {
        super(adminUser, allUser, allCamp);
    }
    
        
    /**
     * Displays the main menu for administrators, allowing them to select and add user profiles from CSV files.
     */
    public void mainMenu() {
        System.out.println("\nList of file that can be added:");
        Integer profilechoice = -1;
        String selectedFile = null;
        int fileChoice = -1;

        File dir = new File("./xlsxLISTfolder");
        File[] files = dir.listFiles((d, name) -> name.endsWith(".csv"));
        if (files.length == 0) {
            System.out.println("Error! No csv file found!");
            return;
        }
        if (files != null && files.length > 0) {
            int i = 1;
            for (; i <= files.length; i++) {
                System.out.printf("(%d)\t%s\n", i, files[i - 1].getName());
            }
            System.out.printf("(%d)\tExit\n", i);

        }

        do {
            System.out.print("\nSelect a file by number: ");
            fileChoice = MainProgram.scanInt();
            if (fileChoice <= 0 || fileChoice > files.length + 1) {
                System.out.println("Invalid input. Try again!");
            }

        } while (!(fileChoice > 0 && fileChoice <= files.length + 1));
        if (fileChoice == files.length + 1) {
            return;
        }
        selectedFile = files[fileChoice - 1].getName();
        System.out.println("Selected file: " + selectedFile);
        String filePath = "./xlsxLISTfolder/" + selectedFile;

        do {
            System.out.printf("\nSelect type:\n(1)\tStaff\n(2)\tStudent\n" + //
                    "Choice: ");
            profilechoice = MainProgram.scanInt();
            if (profilechoice != 1 && profilechoice != 2) {
                System.out.println("Invalid input. Try again!");
            }
        } while (profilechoice != 1 && profilechoice != 2);

        Roles.ROLES role = Roles.ROLES.STAFF;
        if (profilechoice == 2) {
            role = Roles.ROLES.STUDENT;
        }
        try {
            ArrayList<User> newuser = CSVReader.readCSV(filePath, role);
            allUser.addMultiUser(newuser);
            SerializationUtil.saveObj(allUser, MainProgram.mainObj_filename);
            System.out.printf("\nSuccesfully added\n");
            return;
        } catch (Exception e) {
            System.out.printf("\nOperation failed!\n");
            return;
        }
    }
}