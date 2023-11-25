package Camp;

import java.io.Serializable;

import Users.*;

public class CampUserManager implements Serializable {
    private CampParticipantManager campParticipantManager;
    private CampOrganiserManager campOrganiserManager;

    public CampUserManager(User user, int committeeSize, int totalSize) {
        campParticipantManager = new CampParticipantManager(committeeSize, totalSize);
        campOrganiserManager = new CampOrganiserManager(user);

    }

    public CampParticipantManager getParticipantManager() {
        return this.campParticipantManager;
    }

    public CampOrganiserManager getCampOrganiserManager() {
        return this.campOrganiserManager;
    }

    public String toString() {
        return campOrganiserManager.toString() + campParticipantManager.toString();
    }
}
