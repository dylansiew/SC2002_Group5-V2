package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import Users.*;

/**
 * `SerializationUtil` provides utility methods for serializing and deserializing objects
 * to/from files. It also includes a method for initializing the main application object and adding an admin user.
 * This class is responsible for managing the persistence of the application's data.
 */

public class SerializationUtil {

    private static void deleteFile(String filePath) {
        File myObj = new File(filePath);
        myObj.delete();
    }
    /** save the changes made by user into the {@code @mainObj} before after logging out
     * @param obj the {@code MainObj} instance
     * @param filePath the file path to {@code mainObj}. It is {@code mainObj.ser} in this application
    */
    public static void saveObj(Object obj, String filePath) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.mkdirs();
            }

            deleteFile(filePath);

            FileOutputStream fileOut = new FileOutputStream(filePath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(obj);
            objectOut.close();
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** Add an admin 
     * It is used when the program stars and there are no users
     * @param mainObj To retrieve all users and 
    */
    private static void addAdmin(MainObj mainObj) {
        AdminUser newuser = new AdminUser("admin", "admin@ntu.edu.sg", Faculty.FACULTY_TYPE.UNIVERSE);
        if (mainObj != null && newuser != null) {
            AllUser allUser = mainObj.getAllUser();
            if (allUser != null) {
                allUser.addUser(newuser);
            }
        }
    }

    /** Load the {@code mainObj} before running the main application
     * @param filepath the path to {@code mainObj}. It is {@code mainObj.ser} in this application
    */
    public static MainObj loadMainObj(String filePath) {
        File myObj = new File(filePath);
        MainObj mainObj = null;
        if (!myObj.exists()) {
            mainObj = new MainObj();
            addAdmin(mainObj);
            SerializationUtil.saveObj(mainObj, filePath);
        } else {
            try {
                FileInputStream fileIn = new FileInputStream(filePath);
                ObjectInputStream objectIn = new ObjectInputStream(fileIn);
                mainObj = (MainObj) objectIn.readObject();
                objectIn.close();
                fileIn.close();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return mainObj;

    }
}
