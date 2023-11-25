package Menu.CampHandler;

import Controller.*;
import Users.*;
/**
 * The `CampHandler` class is responsible for managing different aspects of a camp, including modifications,
 * participants, and viewing camp details. It provides access to three sub-handlers for these purposes:
 * `CampModifyHandler`, `CampParticipantHandler`, and `CampViewHandler`.
 */
public class CampHandler {
    private CampModifyHandler campModifyHandler;
    private CampParticipantHandler campParticipantHandler;
    private CampViewHandler campViewHandler;
    /**
     * Initializes a new instance of the `CampHandler` class with the specified `AllCamp` and `User` objects.
     * @param allCamp The `AllCamp` object containing information about all camps.
     * @param user    The `User` object representing the user interacting with camp-related operations.
     */
    public CampHandler(AllCamp allCamp, User user) {
        campModifyHandler = new CampModifyHandler(allCamp, user);
        campParticipantHandler = new CampParticipantHandler(allCamp, user);
        campViewHandler = new CampViewHandler(allCamp, user);
    }
    /**
     * Gets the `CampModifyHandler` associated with this `CampHandler`.
     * @return The `CampModifyHandler` for camp modification operations.
     */
    public CampModifyHandler getCampModifyHandler() {
        return this.campModifyHandler;
    }
    /**
     * Gets the `CampParticipantHandler` associated with this `CampHandler`.
     * @return The `CampParticipantHandler` for camp participant-related operations.
     */
    public CampParticipantHandler getCampParticipantHandler() {
        return this.campParticipantHandler;
    }
    /**
     * Gets the `CampViewHandler` associated with this `CampHandler`.
     * @return The `CampViewHandler` for camp viewing operations.
     */
    public CampViewHandler getCampViewHandler() {
        return this.campViewHandler;
    }
}
