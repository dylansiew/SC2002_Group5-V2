package Menu;

import Controller.AllCamp;
import Controller.AllUser;
import Menu.CampHandler.CampHandler;
import Users.User;

public abstract class UserMenu {
    User user;
    AllCamp allCamp;
    AllUser allUser;
    CampHandler campHandler;

    public UserMenu(User user, AllUser allUser, AllCamp allCamp) {
        this.user = user;
        this.allCamp = allCamp;
        this.allUser = allUser;
        this.campHandler = new CampHandler(allCamp, user);
    }

    public abstract void mainMenu();
}
