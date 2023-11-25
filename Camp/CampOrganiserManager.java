package Camp;

import java.io.Serializable;

import Users.*;

public class CampOrganiserManager implements Serializable {
    private User organiser;

    public CampOrganiserManager(User user) {
        this.organiser = user;
    }

    public User getOrganiser() {
        return this.organiser;
    }

    @Override
    public String toString() {
        return "Organiser: " + organiser.getName() + "\n";
    }
}
