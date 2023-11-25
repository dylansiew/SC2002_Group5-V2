package Menu;

import java.util.*;
import Controller.*;
import Program.*;
import Users.*;

public class AuthenticationMenu extends UserMenu {
    public AuthenticationMenu(User user, AllUser allUser, AllCamp allCamp) {
        super(user, allUser, allCamp);
    }

    public static final int MAX_RETRY = 3;

    public static User loginMenu(AllUser allUser) {
        int retry = MAX_RETRY;

        ArrayList<User> userArray = allUser.getUserArray();

        System.out.println("\nEnter username and password to log in");
        while (true) {
            System.out.print("Username: ");
            String username = MainProgram.sc.nextLine();

            for (User user : userArray) {
                Authentication auth = user.getAuthentication();
                if (username.toLowerCase().equals(auth.getUsername().toLowerCase())) {
                    System.out.printf("Hello, %s! Key in your password to proceed\n", user.getName());
                    do {
                        System.out.print("Password: ");
                        String password = MainProgram.sc.nextLine();
                        auth.setAuthenticated(username, password);
                        retry -= 1;
                    } while (!auth.getauthenticated() && retry > 0);
                    if (retry == 0 && !auth.getauthenticated()) {
                        System.out.println("\nToo many failed attemps!");
                        return null;
                    }
                    System.out.println();
                    if (auth.getauthenticated()) {
                        if (auth.getFirstLogin()) {
                            System.out.println("Please change your password on your first login!");
                            changePasswordMenu(user);
                            auth.setFirstLogin(false);
                        }
                        return user;
                    }
                }
            }
        }
    }

    public void mainMenu() {

        String menuText = "\n(1)\tView profile\n(2)\tChange password\n(3)\tExit\nChoice: ";
        int choice = 0;
        while (true) {
            System.out.print(menuText);
            choice = MainProgram.scanInt();
            switch (choice) {
                case 1:
                    System.out.println(user.toString());
                    break;
                case 2:
                    changePasswordMenu(user);
                    break;
                default:
                    return;
            }
        }
    }

    private static void changePasswordMenu(User user) {

        while (true) {
            System.out.print("Enter new password: ");
            Authentication auth = user.getAuthentication();
            String password1 = MainProgram.sc.nextLine();
            if (auth.checkVsOldPassword(password1)) {
                System.out.println("New password cannot be same as old password! Please try again...");
                continue;
            }

            System.out.print("Re-enter new password: ");
            String password2 = MainProgram.sc.nextLine();
            if (password1.equals(password2)) {

                auth.setPassword(password2);
                System.out.println("Password updated successfully!");
                return;
            } else {
                System.out.println("Password does not match. Please try again...");
            }
        }

    }
}
