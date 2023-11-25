package Camp;

import java.io.Serializable;
import java.util.ArrayList;

import Users.*;

public abstract class CampParticipant implements Serializable {
    ArrayList<User> participantArrayList = new ArrayList<>();
    private Roles.ROLES role;
    private int totalSlots;

    public CampParticipant(Roles.ROLES role, int totalSlots) {
        if (role == null) {
            return;
        }
        this.role = role;
        this.totalSlots = totalSlots;
    }

    public boolean addParticipant(User user) {
        if (user == null) {
            System.out.println("Error adding user");
            return false;
        }
        if (!(participantArrayList.contains(user)) && this.hasEmptySlots()) {
            participantArrayList.add(user);
            return true;
        } else {
            System.out.println("Error adding user");
            return false;
        }
    }

    public boolean removeParticipant(User user) {
        if (user == null || !(participantArrayList.contains(user))) {
            return false;
        }
        participantArrayList.remove(user);
        return true;
    }

    public ArrayList<User> getParticipantsArrayList() {
        return this.participantArrayList;
    }

    public int getTotalSlot() {
        return this.totalSlots;
    }

    public void setTotalSlot(int totalSlots) {
        if (totalSlots < 0) {
            System.out.println("Slots cannot be negative, failed to update.");
            return;
        }
        this.totalSlots = totalSlots;
    }

    public int getParticipantSize() {
        return this.participantArrayList.size();
    }

    public boolean hasEmptySlots() {
        return this.participantArrayList.size() < totalSlots;
    }

    @Override
    public String toString() {
        int i = 1;
        String info = "\n" + role + " (" + participantArrayList.size() + "):\n";
        for (User user : participantArrayList) {
            info += i + ":\t" + user.toString() + "\n";
            i += 1;
        }
        return info;
    }
}
