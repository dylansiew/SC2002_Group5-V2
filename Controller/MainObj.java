package Controller;

import java.io.Serializable;

/**
 * The `MainObj` class represents the main object that contains references to the core components of the Camp Management System.
 * It includes references to the `AllUser` and `AllCamp` instances, which store user and camp data, respectively.
 */
public class MainObj implements Serializable {
    private AllUser allUser;
    private AllCamp allCamp;
    /**
     * Constructs a new `MainObj` instance with references to the singleton instances of `AllUser` and `AllCamp`.
     */
    public MainObj() {
        allCamp = AllCamp.getInstance();
        allUser = AllUser.getInstance();
    }
    /**
     * Constructs a new `MainObj` instance with a provided `AllUser` instance and a reference to the singleton instance of `AllCamp`.
     * @param allUser The `AllUser` instance to be associated with this `MainObj`.
     */
    public MainObj(AllUser allUser) {
        this.allUser = allUser;
        allCamp = AllCamp.getInstance();
    }
    /**
     * Get the `AllUser` instance associated with this `MainObj`.
     * @return The `AllUser` instance.
     */
    public AllUser getAllUser() {
        return this.allUser;
    }
    /**
     * Get the `AllCamp` instance associated with this `MainObj`.
     * @return The `AllCamp` instance.
     */
    public AllCamp getAllCamp() {
        return this.allCamp;
    }
}
