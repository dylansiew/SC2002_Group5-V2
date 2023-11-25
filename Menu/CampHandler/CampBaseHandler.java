package Menu.CampHandler;

import Controller.*;
import Users.*;

public abstract class CampBaseHandler {
    AllCamp allCamp;
    User user;

    public CampBaseHandler(AllCamp allCamp, User user) {
        this.allCamp = allCamp;
        this.user = user;
    }
}