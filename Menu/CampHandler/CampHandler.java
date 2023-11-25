package Menu.CampHandler;

import Controller.*;
import Users.*;

public class CampHandler {
    private CampModifyHandler campModifyHandler;
    private CampParticipantHandler campParticipantHandler;
    private CampViewHandler campViewHandler;

    public CampHandler(AllCamp allCamp, User user) {
        campModifyHandler = new CampModifyHandler(allCamp, user);
        campParticipantHandler = new CampParticipantHandler(allCamp, user);
        campViewHandler = new CampViewHandler(allCamp, user);
    }

    public CampModifyHandler getCampModifyHandler() {
        return this.campModifyHandler;
    }

    public CampParticipantHandler getCampParticipantHandler() {
        return this.campParticipantHandler;
    }

    public CampViewHandler getCampViewHandler() {
        return this.campViewHandler;
    }
}
