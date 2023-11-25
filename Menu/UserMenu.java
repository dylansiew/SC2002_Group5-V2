package Menu;

import Controller.AllCamp;
import Controller.AllUser;
import Menu.CampHandler.CampHandler;
import Users.User;
/**
 * The `UserMenu` abstract class represents a menu for a user with common functionalities.
 */
public abstract class UserMenu {
    User user;
    AllCamp allCamp;
    AllUser allUser;
    CampHandler campHandler;
    /**
     * Initialize the specific user to stick to with the `UserMenu`
     * @param user     The user for whom the menu is created.
     * @param allUser  The collection of all users in the system.
     * @param allCamp  The collection of all camps in the system.
     */
    public UserMenu(User user, AllUser allUser, AllCamp allCamp) {
        this.user = user;
        this.allCamp = allCamp;
        this.allUser = allUser;
        this.campHandler = new CampHandler(allCamp, user);
    }
    /**
     * Represents the main menu for the user with common functionalities.
     */
    public abstract void mainMenu();
}
