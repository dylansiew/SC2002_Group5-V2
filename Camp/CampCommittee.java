package Camp;

import Users.Roles;
/**
 * The `CampCommittee` class represents committee members in a camp. Committee members have specific roles and responsibilities within the camp.
 * It extends the `CampParticipant` class.
 */
public class CampCommittee extends CampParticipant{
    public static final int MAX_COMMITTEE_SLOTS = 10;
    /**
    * Constructs a new `CampCommittee` instance and specified total slots available for committee
     * @param totalSLots The total number of committee slots available in the camp.
     */
    public CampCommittee(int totalSLots) {
        super(Roles.ROLES.COMMITTEE, totalSLots);
    }
}
