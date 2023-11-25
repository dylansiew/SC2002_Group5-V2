package Controller;

import java.io.Serializable;

public class MainObj implements Serializable {
    private AllUser allUser;
    private AllCamp allCamp;

    public MainObj() {
        allCamp = AllCamp.getInstance();
        allUser = AllUser.getInstance();
    }

    public MainObj(AllUser allUser) {
        this.allUser = allUser;
        allCamp = AllCamp.getInstance();
    }

    public AllUser getAllUser() {
        return this.allUser;
    }

    public AllCamp getAllCamp() {
        return this.allCamp;
    }
}
