package Program;

import java.text.SimpleDateFormat;
import java.util.*;
import Controller.*;
import Menu.*;
import Users.*;

public class MainProgram {
    public static final String mainObj_filename = "mainObj.ser";
    public static final Scanner sc = new Scanner(System.in);
    public static Date currentDate = new Date();
    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

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
