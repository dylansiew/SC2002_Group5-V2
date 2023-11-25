package Camp;

import Users.Roles;

public class CampCommittee extends CampParticipant {
    public static final int MAX_COMMITTEE_SLOTS = 10;

    public CampCommittee(int totalSLots) {
        super(Roles.ROLES.COMMITTEE, totalSLots);
    }
}
