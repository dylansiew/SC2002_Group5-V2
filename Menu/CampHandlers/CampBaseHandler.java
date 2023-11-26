package Menu.CampHandlers;

import Controller.*;
import Users.*;
/**
 * The abstract class `CampBaseHandler` serves as a base class for handling various operations related to camps.
 * It provides common fields and methods that can be used by concrete camp handlers.
 */
public abstract class CampBaseHandler {
    AllCamp allCamp;
    User user;
    /**
     * Initializes a new instance of the `CampBaseHandler` class with the specified `AllCamp` and `User` objects.
     * @param allCamp The `AllCamp` object containing information about all camps.
     * @param user    The `User` object representing the user interacting with camp-related operations.
     */
    public CampBaseHandler(AllCamp allCamp, User user) {
        this.allCamp = allCamp;
        this.user = user;
    }
}