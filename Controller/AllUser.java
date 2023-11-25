package Controller;

import java.io.Serializable;
import java.util.*;
import Users.*;

public class AllUser implements Serializable {
    private ArrayList<User> userArray = new ArrayList<>();
    private static AllUser instance;

    private AllUser() {
        userArray = new ArrayList<>();
    }

    public static AllUser getInstance() {
        if (instance == null) {
            instance = new AllUser();
        }
        return instance;
    }

    public void addMultiUser(ArrayList<User> newArr) {
        if (newArr == null) {
            return;
        }
        for (User usr : newArr) {
            userArray.add(usr);
        }
    }

    public void addUser(User newUser) {
        if (newUser == null) {
            return;
        }
        userArray.add(newUser);
    }

    public void printAllUser() {
        for (User user : userArray) {
            System.out.println(user.toString());
        }
    }

    public ArrayList<User> getUserArray() {
        return this.userArray;
    }
}
