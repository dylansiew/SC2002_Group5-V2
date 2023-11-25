package Users;

import java.util.*;
import Camp.*;

public class Staff extends User {
    private ArrayList<Camp> campsCreated = new ArrayList<Camp>();

    public Staff(String name, String email, Faculty.FACULTY_TYPE faculty) {
        super(name, email, faculty);
        this.id = User.nextStaffId;
        User.nextStaffId += 1;
    }

    public void addCamp(Camp camp) {
        campsCreated.add(camp);
        Collections.sort(campsCreated);
    }

    public ArrayList<Camp> getCampsCreated() {
        return this.campsCreated;
    }

    public String toString() {
        return "User: Staff " + super.toString();
    }

    public void removeCamp(Camp camp) {
        if (camp == null || !campsCreated.contains(camp)) {
            return;
        }
        campsCreated.remove(camp);
    }
}
