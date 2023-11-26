package Controller;

import java.io.Serializable;
import java.util.*;
import Users.*;

/**
 * `AllCamp` stores the collection of all camps.
 *  It can only be instantiate once
 */
public class AllUser implements Serializable {
    /** An array to store all of the users */
    private ArrayList<User> userArray = new ArrayList<>();
    private static AllUser instance;
    
     /** Private constructor to restrict the creation of multiple instances of AllUser */
    private AllUser(){
        userArray = new ArrayList<>();
    }

    /** Get the singleton instance of AllUser */
    public static AllUser getInstance(){
        if(instance==null){
            instance= new AllUser();
        }
        return instance;
    }
    /** 
     * Add multiple users at a time.
     * Used by the admin user (a.k.a super user).
     * @param newArr The ArrayList of users to be added.
     */
    public void addMultiUser(ArrayList<User> newArr) {
        if(newArr == null){return;}
        for (User usr : newArr) {
            userArray.add(usr);
        }
    }
    /** 
     * Add one user at a time.
     * Used by the admin user (a.k.a super user).
     * @param newUser The user to be added.
     */
    public void addUser(User newUser) {
        if(newUser == null){return;}
        userArray.add(newUser);
    }
    /** 
     * Print out all of users 
    */
    public void printAllUser() {
        for (User user : userArray) {
            System.out.println(user.toString());
        }
    }

    /** Get the `userArray` that storing all of the users */
    public ArrayList<User> getUserArray() {
        return this.userArray;
    }
}
